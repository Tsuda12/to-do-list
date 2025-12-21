package tsuda.br.com.to_do_list.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import tsuda.br.com.to_do_list.utils.MessageUtils;

public record CreateUserRequest(
        @NotBlank(message = MessageUtils.NAME_REQUIRED)
        String name,

        @Email(message = MessageUtils.EMAIL_VALID)
        @NotBlank(message = MessageUtils.EMAIL_REQUIRED)
        String email,

        @NotBlank(message = MessageUtils.PASSWORD_REQUIRED)
        String password
) {}
