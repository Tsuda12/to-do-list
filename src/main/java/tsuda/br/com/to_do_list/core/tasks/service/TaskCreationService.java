package tsuda.br.com.to_do_list.core.tasks.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import tsuda.br.com.to_do_list.core.tasks.dto.request.TaskCreationRequest;

import java.util.Map;

public interface TaskCreationService {

    ResponseEntity<Map<String, Object>> execute(Authentication authentication, TaskCreationRequest request);
}
