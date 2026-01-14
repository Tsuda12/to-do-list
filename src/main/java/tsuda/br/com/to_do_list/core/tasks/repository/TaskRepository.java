package tsuda.br.com.to_do_list.core.tasks.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tsuda.br.com.to_do_list.core.tasks.entity.Task;

public interface TaskRepository extends JpaRepository<Task, String>, JpaSpecificationExecutor<Task> {

    Page<Task> findAll(Pageable pageable);
}
