package tsuda.br.com.to_do_list.core.tasks.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tsuda.br.com.to_do_list.core.tasks.dto.request.TaskCreationRequest;
import tsuda.br.com.to_do_list.core.tasks.service.impl.TaskCreationServiceImpl;
import tsuda.br.com.to_do_list.security.SecurityConfig;

import java.util.Map;

@RestController
@RequestMapping("/task")
@Tag(name = "Task", description = "Endpoints da entidade Task")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class TaskController {

    private final TaskCreationServiceImpl taskCreationService;

    public TaskController(TaskCreationServiceImpl taskCreationService) {
        this.taskCreationService = taskCreationService;
    }

    @Operation(summary = "Criação de tarefa")
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(Authentication authentication,
                                                      @RequestBody @Valid TaskCreationRequest request) {

        return taskCreationService.execute(authentication, request);
    }
}
