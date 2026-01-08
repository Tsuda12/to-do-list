package tsuda.br.com.to_do_list.core.tasks.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import tsuda.br.com.to_do_list.core.tasks.dto.request.TaskCreationRequest;
import tsuda.br.com.to_do_list.core.tasks.entity.Task;
import tsuda.br.com.to_do_list.core.tasks.mapper.TaskMapper;
import tsuda.br.com.to_do_list.core.tasks.repository.TaskRepository;
import tsuda.br.com.to_do_list.core.tasks.service.TaskCreationService;
import tsuda.br.com.to_do_list.core.user.entity.User;
import tsuda.br.com.to_do_list.core.user.repository.UserRepository;
import tsuda.br.com.to_do_list.exceptions.GenericNotFoundException;
import tsuda.br.com.to_do_list.security.JWTUserData;
import tsuda.br.com.to_do_list.utils.MessageUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class TaskCreationServiceImpl implements TaskCreationService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskCreationServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> execute(Authentication authentication, TaskCreationRequest request) {
        JWTUserData jwtUser = (JWTUserData) authentication.getPrincipal();

        User user = userRepository.findByEmail(jwtUser.email())
                .orElseThrow(() -> new GenericNotFoundException(MessageUtils.USER_NOT_EXISTS));

        Task task = TaskMapper.toEntity(request, user);

        taskRepository.save(task);

        Map<String, Object> body = new HashMap<>();
        body.put(MessageUtils.RESPONSE, MessageUtils.TASK_CREATED);

        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }
}
