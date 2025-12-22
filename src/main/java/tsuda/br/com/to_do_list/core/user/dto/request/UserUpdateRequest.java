package tsuda.br.com.to_do_list.core.user.dto.request;

import jakarta.validation.constraints.Email;
import tsuda.br.com.to_do_list.utils.MessageUtils;

public record UserUpdateRequest(
        String name,

        @Email(message = MessageUtils.EMAIL_VALID)
        String email,

        String password
) {}
