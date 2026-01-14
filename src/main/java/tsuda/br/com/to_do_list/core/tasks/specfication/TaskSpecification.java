package tsuda.br.com.to_do_list.core.tasks.specfication;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;
import tsuda.br.com.to_do_list.core.tasks.entity.Task;

public class TaskSpecification {

    public static Specification<Task> findByUser(String userId) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(userId)) {
                return null;
            }

            return builder.equal(root.get("user").get("id"), userId);
        };
    }
}
