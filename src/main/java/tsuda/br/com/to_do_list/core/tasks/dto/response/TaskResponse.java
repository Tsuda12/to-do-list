package tsuda.br.com.to_do_list.core.tasks.dto.response;

import java.time.LocalDateTime;

public record TaskResponse(
        String name,
        String description,
        LocalDateTime startedAt,
        LocalDateTime endedAt
) {}
