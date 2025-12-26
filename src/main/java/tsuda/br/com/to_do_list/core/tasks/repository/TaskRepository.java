package tsuda.br.com.to_do_list.core.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tsuda.br.com.to_do_list.core.tasks.entity.Task;

public interface TaskRepository extends JpaRepository<Task, String>, JpaSpecificationExecutor<Task> {
}
