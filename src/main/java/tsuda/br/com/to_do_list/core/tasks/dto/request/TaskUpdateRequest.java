package tsuda.br.com.to_do_list.core.tasks.dto.request;

import java.time.LocalDateTime;

public record TaskUpdateRequest(
        String name,
        String description,
        LocalDateTime startedAt,
        LocalDateTime endedAt
) {}
