package tsuda.br.com.to_do_list.core.user.service;

import org.springframework.http.ResponseEntity;
import tsuda.br.com.to_do_list.core.user.dto.request.CreateUserRequest;

import java.util.Map;

public interface UserCreationService {

    ResponseEntity<Map<String, Object>> execute(CreateUserRequest request);
}
