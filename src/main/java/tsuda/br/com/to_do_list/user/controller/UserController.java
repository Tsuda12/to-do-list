package tsuda.br.com.to_do_list.user.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tsuda.br.com.to_do_list.user.dto.request.CreateUserRequest;
import tsuda.br.com.to_do_list.user.service.CreateUserService;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserService createUserService;

    public UserController(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody @Valid CreateUserRequest request) {
        return createUserService.execute(request);
    }
}
