package tsuda.br.com.to_do_list.core.user.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tsuda.br.com.to_do_list.exceptions.GenericBusinessRuleException;
import tsuda.br.com.to_do_list.core.user.dto.request.CreateUserRequest;
import tsuda.br.com.to_do_list.core.user.entity.User;
import tsuda.br.com.to_do_list.core.user.mapper.UserMapper;
import tsuda.br.com.to_do_list.core.user.repository.UserRepository;
import tsuda.br.com.to_do_list.core.user.service.UserCreationService;
import tsuda.br.com.to_do_list.utils.MessageUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserCreationCreationServiceImpl implements UserCreationService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserCreationCreationServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public ResponseEntity<Map<String, Object>> execute(CreateUserRequest request) {
        Optional<User> existingUserByEmail = userRepository.findByEmail(request.email());

        if (existingUserByEmail.isEmpty()) {
            User user = UserMapper.toEntity(request);
            user.setPassword(encoder.encode(request.password()));

            userRepository.save(user);
        } else {
            throw new GenericBusinessRuleException(MessageUtils.USER_EXISTS);
        }

        Map<String, Object> body = new HashMap<>();
        body.put(MessageUtils.RESPONSE, MessageUtils.USER_CREATED);

        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }
}
