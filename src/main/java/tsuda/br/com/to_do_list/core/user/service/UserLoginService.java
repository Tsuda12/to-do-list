package tsuda.br.com.to_do_list.core.user.service;

import org.springframework.http.ResponseEntity;
import tsuda.br.com.to_do_list.core.user.dto.request.UserLoginRequest;

import java.util.Map;

public interface UserLoginService {
    ResponseEntity<Map<String, Object>> execute(UserLoginRequest request);
}
