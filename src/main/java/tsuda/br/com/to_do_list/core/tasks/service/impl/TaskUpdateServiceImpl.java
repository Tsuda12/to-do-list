package tsuda.br.com.to_do_list.core.tasks.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import tsuda.br.com.to_do_list.core.tasks.dto.request.TaskUpdateRequest;
import tsuda.br.com.to_do_list.core.tasks.entity.Task;
import tsuda.br.com.to_do_list.core.tasks.mapper.TaskMapper;
import tsuda.br.com.to_do_list.core.tasks.repository.TaskRepository;
import tsuda.br.com.to_do_list.core.tasks.service.TaskUpdateService;
import tsuda.br.com.to_do_list.core.user.entity.User;
import tsuda.br.com.to_do_list.core.user.repository.UserRepository;
import tsuda.br.com.to_do_list.exceptions.GenericBusinessRuleException;
import tsuda.br.com.to_do_list.exceptions.GenericNotFoundException;
import tsuda.br.com.to_do_list.security.JWTUserData;
import tsuda.br.com.to_do_list.utils.MessageUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class TaskUpdateServiceImpl implements TaskUpdateService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskUpdateServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Map<String, Object>> execute(Authentication authentication, String taskId, TaskUpdateRequest request) {
        JWTUserData jwtUser = (JWTUserData) authentication.getPrincipal();

        User user = userRepository.findByEmail(jwtUser.email())
                .orElseThrow(() -> new GenericNotFoundException(MessageUtils.USER_NOT_EXISTS));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new GenericNotFoundException(MessageUtils.TASK_NOT_FOUND));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new GenericBusinessRuleException(MessageUtils.TASK_BELONGS_ANOTHER_USER);
        }

        Task updatedTask = TaskMapper.toUpdatedTask(task, request);
        taskRepository.save(updatedTask);

        Map<String, Object> body = new HashMap<>();
        body.put(MessageUtils.RESPONSE, MessageUtils.TASK_UPDATED);

        return new ResponseEntity<>(body, HttpStatus.OK);

        // TODO: Finalizar tarefa
    }
}
