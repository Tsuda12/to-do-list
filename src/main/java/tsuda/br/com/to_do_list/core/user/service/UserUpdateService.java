package tsuda.br.com.to_do_list.core.user.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import tsuda.br.com.to_do_list.core.user.dto.request.UserUpdateRequest;

import java.util.Map;

public interface UserUpdateService {
    ResponseEntity<Map<String, Object>> execute(Authentication principal, String id, UserUpdateRequest request);
}
