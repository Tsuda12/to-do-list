package tsuda.br.com.to_do_list.core.tasks.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tsuda.br.com.to_do_list.utils.MessageUtils;

import java.time.LocalDateTime;

public record TaskCreationRequest(
        @NotBlank(message = MessageUtils.NAME_REQUIRED)
        String name,

        @NotBlank(message = MessageUtils.DESCRIPTION_REQUIRED)
        String description,

        @NotNull(message = MessageUtils.STARTED_AT_REQUIRED)
        LocalDateTime startedAt,

        @NotNull(message = MessageUtils.ENDED_AT_REQUIRED)
        LocalDateTime endedAt
) {}
