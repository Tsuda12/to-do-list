package tsuda.br.com.to_do_list.core.tasks.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tsuda.br.com.to_do_list.core.tasks.dto.request.TaskCreationRequest;
import tsuda.br.com.to_do_list.core.tasks.dto.request.TaskUpdateRequest;
import tsuda.br.com.to_do_list.core.tasks.service.GetAllTasksService;
import tsuda.br.com.to_do_list.core.tasks.service.TaskFinishService;
import tsuda.br.com.to_do_list.core.tasks.service.TaskUpdateService;
import tsuda.br.com.to_do_list.core.tasks.service.impl.TaskCreationServiceImpl;
import tsuda.br.com.to_do_list.core.tasks.service.impl.TaskUpdateServiceImpl;
import tsuda.br.com.to_do_list.security.SecurityConfig;

import java.util.Map;

@RestController
@RequestMapping("/task")
@Tag(name = "Task", description = "Endpoints da entidade Task")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class TaskController {

    private final TaskCreationServiceImpl taskCreationService;
    private final GetAllTasksService getAllTasksService;
    private final TaskUpdateService taskUpdateService;
    private final TaskFinishService taskFinishService;

    public TaskController(TaskCreationServiceImpl taskCreationService,
                          GetAllTasksService getAllTasksService,
                          TaskUpdateService taskUpdateService,
                          TaskFinishService taskFinishService) {

        this.taskCreationService = taskCreationService;
        this.getAllTasksService = getAllTasksService;
        this.taskUpdateService = taskUpdateService;
        this.taskFinishService = taskFinishService;
    }

    @Operation(summary = "Criação de tarefa")
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(Authentication authentication,
                                                      @RequestBody @Valid TaskCreationRequest request) {

        return taskCreationService.execute(authentication, request);
    }

    @Operation(summary = "Mostrar todas as tarefas do usuário")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(Authentication authentication,
                                                      @RequestParam(defaultValue = "1") int page,
                                                      @RequestParam(defaultValue = "5") int size) {

        return getAllTasksService.execute(authentication, page, size);
    }

    @Operation(summary = "Atualizar tarefa")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateById(Authentication authentication,
                                                          @PathVariable(name = "id") String taskId,
                                                          @RequestBody TaskUpdateRequest request) {

        return taskUpdateService.execute(authentication, taskId, request);
    }

    @Operation(summary = "Finalizar tarefa")
    @PutMapping("/finish/{id}")
    public ResponseEntity<Map<String, Object>> finishTask(Authentication authentication,
                                                          @PathVariable(name = "id") String taskId) {

        return taskFinishService.execute(authentication, taskId);
    }
}
