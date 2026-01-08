package tsuda.br.com.to_do_list.core.tasks.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tsuda.br.com.to_do_list.core.user.entity.User;

import java.time.LocalDateTime;

@Table(name = "tasks")
@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_fk_id")
    private User user;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private boolean finished;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
