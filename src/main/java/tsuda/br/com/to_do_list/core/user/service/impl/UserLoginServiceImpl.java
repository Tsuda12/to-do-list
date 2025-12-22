package tsuda.br.com.to_do_list.core.user.service.impl;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tsuda.br.com.to_do_list.core.user.dto.request.UserLoginRequest;
import tsuda.br.com.to_do_list.core.user.entity.User;
import tsuda.br.com.to_do_list.core.user.service.UserLoginService;
import tsuda.br.com.to_do_list.security.TokenConfig;
import tsuda.br.com.to_do_list.utils.MessageUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public UserLoginServiceImpl(AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    @Override
    public ResponseEntity<Map<String, Object>> execute(@RequestBody @Valid UserLoginRequest request) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        );

        Authentication authentication = authenticationManager.authenticate(userAndPass);

        User user = (User) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);

        Map<String, Object> body = new HashMap<>();
        body.put(MessageUtils.RESPONSE, token);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
