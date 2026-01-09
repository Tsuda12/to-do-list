package tsuda.br.com.to_do_list.core.tasks.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
        @Schema(example = "2026-01-09T13:30:00", type = "string", format = "date-time")
        LocalDateTime startedAt,

        @NotNull(message = MessageUtils.ENDED_AT_REQUIRED)
        @Schema(example = "2026-01-09T14:30:00", type = "string", format = "date-time")
        LocalDateTime endedAt
) {}
