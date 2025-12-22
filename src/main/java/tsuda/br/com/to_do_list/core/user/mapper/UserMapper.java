package tsuda.br.com.to_do_list.core.user.mapper;

import org.mapstruct.Mapper;
import tsuda.br.com.to_do_list.core.user.dto.request.CreateUserRequest;
import tsuda.br.com.to_do_list.core.user.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(CreateUserRequest request);
}
