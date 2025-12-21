package tsuda.br.com.to_do_list.user.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tsuda.br.com.to_do_list.user.dto.request.CreateUserRequest;
import tsuda.br.com.to_do_list.user.entity.User;
import tsuda.br.com.to_do_list.user.enums.UserRolesEnum;
import tsuda.br.com.to_do_list.user.mapper.UserMapper;
import tsuda.br.com.to_do_list.user.repository.UserRepository;
import tsuda.br.com.to_do_list.user.service.CreateUserService;
import tsuda.br.com.to_do_list.utils.MessageUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class CreateUserServiceImpl implements CreateUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public CreateUserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public ResponseEntity<Map<String, Object>> execute(CreateUserRequest request) {
        User user = UserMapper.toEntity(request);

        encoder.encode(user.getPassword());

        userRepository.save(user);

        Map<String, Object> body = new HashMap<>();
        body.put(MessageUtils.RESPONSE, MessageUtils.USER_CREATED);

        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }
}
