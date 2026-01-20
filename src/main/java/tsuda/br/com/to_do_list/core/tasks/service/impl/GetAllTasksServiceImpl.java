package tsuda.br.com.to_do_list.core.tasks.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import tsuda.br.com.to_do_list.core.tasks.dto.response.TaskResponse;
import tsuda.br.com.to_do_list.core.tasks.entity.Task;
import tsuda.br.com.to_do_list.core.tasks.mapper.TaskMapper;
import tsuda.br.com.to_do_list.core.tasks.repository.TaskRepository;
import tsuda.br.com.to_do_list.core.tasks.service.GetAllTasksService;
import tsuda.br.com.to_do_list.core.tasks.specfication.TaskSpecification;
import tsuda.br.com.to_do_list.core.user.entity.User;
import tsuda.br.com.to_do_list.core.user.repository.UserRepository;
import tsuda.br.com.to_do_list.exceptions.GenericNotFoundException;
import tsuda.br.com.to_do_list.security.JWTUserData;
import tsuda.br.com.to_do_list.utils.MessageUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetAllTasksServiceImpl implements GetAllTasksService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public GetAllTasksServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> execute(Authentication authentication, int page, int size) {
        JWTUserData jwtUser = (JWTUserData) authentication.getPrincipal();

        User user = userRepository.findByEmail(jwtUser.email())
                .orElseThrow(() -> new GenericNotFoundException(MessageUtils.USER_NOT_EXISTS));

        Page<Task> allTasks = taskRepository.findAll(
                TaskSpecification.findByUser(user.getId()).and(TaskSpecification.findByFinished()),
                PageRequest.of(page > 0 ? page - 1 : 0, size, Sort.by(Sort.Order.desc("startedAt")))
        );

        Page<TaskResponse> response = allTasks.map(TaskMapper::toResponse);

        Map<String, Object> body = new HashMap<>();
        body.put(MessageUtils.RESPONSE, response);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
