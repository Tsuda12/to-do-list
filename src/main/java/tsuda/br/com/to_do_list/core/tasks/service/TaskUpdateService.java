package tsuda.br.com.to_do_list.core.tasks.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import tsuda.br.com.to_do_list.core.tasks.dto.request.TaskUpdateRequest;

import java.util.Map;

public interface TaskUpdateService {
    ResponseEntity<Map<String, Object>> execute(Authentication authentication, String taskId, TaskUpdateRequest request);
}
