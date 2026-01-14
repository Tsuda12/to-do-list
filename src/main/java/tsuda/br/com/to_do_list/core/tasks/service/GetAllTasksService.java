package tsuda.br.com.to_do_list.core.tasks.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Map;

public interface GetAllTasksService {
    ResponseEntity<Map<String, Object>> execute(Authentication authentication, int page, int size);
}
