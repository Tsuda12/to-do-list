package tsuda.br.com.to_do_list.core.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tsuda.br.com.to_do_list.core.user.dto.request.UserUpdateRequest;
import tsuda.br.com.to_do_list.core.user.service.UserLoginService;
import tsuda.br.com.to_do_list.core.user.service.UserUpdateService;
import tsuda.br.com.to_do_list.security.SecurityConfig;
import tsuda.br.com.to_do_list.core.user.dto.request.CreateUserRequest;
import tsuda.br.com.to_do_list.core.user.dto.request.UserLoginRequest;
import tsuda.br.com.to_do_list.core.user.service.UserCreationService;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Endpoints da entidade User")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class UserController {

    private final UserCreationService userCreationService;
    private final UserLoginService userLoginService;
    private final UserUpdateService userUpdateService;

    public UserController(UserCreationService userCreationService,
                          UserLoginService userLoginService,
                          UserUpdateService userUpdateService) {

        this.userCreationService = userCreationService;
        this.userLoginService = userLoginService;
        this.userUpdateService = userUpdateService;
    }

    @Operation(summary = "Criação de usuário")
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> create(@RequestBody @Valid CreateUserRequest request) {
        return userCreationService.execute(request);
    }

    @Operation(summary = "Login de usuário")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody @Valid UserLoginRequest request) {
        return userLoginService.execute(request);
    }

    @Operation(summary = "Atualização de usuário")
    @PutMapping("/{email}")
    public ResponseEntity<Map<String, Object>> update(Authentication authentication,
                                                      @PathVariable String email,
                                                      @RequestBody @Valid UserUpdateRequest request) {

        return userUpdateService.execute(authentication, email, request);
    }
}
