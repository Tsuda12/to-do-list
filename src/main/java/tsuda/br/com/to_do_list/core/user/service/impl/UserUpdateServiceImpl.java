package tsuda.br.com.to_do_list.core.user.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tsuda.br.com.to_do_list.core.user.dto.request.UserUpdateRequest;
import tsuda.br.com.to_do_list.core.user.entity.User;
import tsuda.br.com.to_do_list.core.user.mapper.UserMapper;
import tsuda.br.com.to_do_list.core.user.repository.UserRepository;
import tsuda.br.com.to_do_list.core.user.service.UserUpdateService;
import tsuda.br.com.to_do_list.exceptions.GenericBusinessRuleException;
import tsuda.br.com.to_do_list.exceptions.GenericNotFoundException;
import tsuda.br.com.to_do_list.security.JWTUserData;
import tsuda.br.com.to_do_list.utils.MessageUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserUpdateServiceImpl implements UserUpdateService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserUpdateServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public ResponseEntity<Map<String, Object>> execute(Authentication authentication, String email, UserUpdateRequest request) {
        JWTUserData jwtUser = (JWTUserData) authentication.getPrincipal();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new GenericNotFoundException(MessageUtils.USER_NOT_EXISTS));

        if (!jwtUser.email().equals(user.getEmail())) {
            throw new AccessDeniedException(MessageUtils.USER_ACCESS_DENIED);
        }

        Optional<User> validateEmailRequest = userRepository.findByEmail(request.email());
        if (validateEmailRequest.isPresent()) {
            throw new GenericBusinessRuleException(MessageUtils.USER_EXISTS);
        }

        User updatedUser = UserMapper.toUpdatedUser(user, request);
        updatedUser.setPassword(encoder.encode(request.password()));

        userRepository.save(updatedUser);

        Map<String, Object> body = new HashMap<>();
        body.put(MessageUtils.RESPONSE, MessageUtils.USER_UPDATED);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
