package tsuda.br.com.to_do_list.core.tasks.mapper;

import tsuda.br.com.to_do_list.core.tasks.dto.request.TaskCreationRequest;
import tsuda.br.com.to_do_list.core.tasks.entity.Task;
import tsuda.br.com.to_do_list.core.user.entity.User;

import java.time.LocalDateTime;

public class TaskMapper {

    public static Task toEntity(TaskCreationRequest request, User user) {
        Task task = new Task();

        task.setName(request.name());
        task.setDescription(request.description() != null ? request.description() : null);
        task.setUser(user);
        task.setStartedAt(request.startedAt());
        task.setEndedAt(request.endedAt());
        task.setFinished(false);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(null);

        return task;
    }
}
