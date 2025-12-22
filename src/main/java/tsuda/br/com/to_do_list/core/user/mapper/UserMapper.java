package tsuda.br.com.to_do_list.core.user.mapper;

import tsuda.br.com.to_do_list.core.user.dto.request.CreateUserRequest;
import tsuda.br.com.to_do_list.core.user.dto.request.UserUpdateRequest;
import tsuda.br.com.to_do_list.core.user.entity.User;
import tsuda.br.com.to_do_list.core.user.enums.UserRolesEnum;

public class UserMapper {

    public static User toEntity(CreateUserRequest request) {
        User user = new User();

        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setRole(UserRolesEnum.ROLE_USER);

        return user;
    }

    public static User toUpdatedUser(User user, UserUpdateRequest request) {
        if (request.name() != null && !request.name().isEmpty()) {
            user.setName(request.name());
        }

        if (request.email() != null && !request.email().isEmpty()) {
            user.setEmail(request.email());
        }

        if (request.password() != null && !request.password().isEmpty()) {
            user.setPassword(request.password());
        }

        return user;
    }
}
