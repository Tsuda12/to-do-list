package tsuda.br.com.to_do_list.core.user.mapper;

import tsuda.br.com.to_do_list.core.user.dto.request.CreateUserRequest;
import tsuda.br.com.to_do_list.core.user.entity.User;
import tsuda.br.com.to_do_list.core.user.enums.UserRolesEnum;

public class UserMapper {

    public static User toEntity(CreateUserRequest request) {
        User user = new User();

        user.setName(request.name());
        user.setEmail(request.email());
        user.setRole(UserRolesEnum.ROLE_USER);

        return user;
    }
}
