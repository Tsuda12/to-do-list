package tsuda.br.com.to_do_list.user.service;

import org.springframework.http.ResponseEntity;
import tsuda.br.com.to_do_list.user.dto.request.CreateUserRequest;

import java.util.Map;

public interface CreateUserService {

    ResponseEntity<Map<String, Object>> execute(CreateUserRequest request);
}
