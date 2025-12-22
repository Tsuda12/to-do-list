package tsuda.br.com.to_do_list.core.user.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tsuda.br.com.to_do_list.core.user.dto.request.CreateUserRequest;
import tsuda.br.com.to_do_list.core.user.dto.request.UserUpdateRequest;
import tsuda.br.com.to_do_list.core.user.entity.User;
import tsuda.br.com.to_do_list.core.user.enums.UserRolesEnum;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    private final PasswordEncoder encoder;

    public UserMapper(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public User toEntity(CreateUserRequest request) {
        User user = new User();

        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(encoder.encode(request.password()));
        user.setRole(UserRolesEnum.ROLE_USER);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(null);

        return user;
    }

    public User toUpdatedUser(User user, UserUpdateRequest request) {
        if (request.name() != null && !request.name().isEmpty()) {
            user.setName(request.name());
            user.setUpdatedAt(LocalDateTime.now());
        }

        if (request.email() != null && !request.email().isEmpty()) {
            user.setEmail(request.email());
            user.setUpdatedAt(LocalDateTime.now());
        }

        if (request.password() != null && !request.password().isEmpty()) {
            user.setPassword(encoder.encode(request.password()));
            user.setUpdatedAt(LocalDateTime.now());
        }

        return user;
    }
}
