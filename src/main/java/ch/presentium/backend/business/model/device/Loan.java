package ch.presentium.backend.business.model.device;

import ch.presentium.backend.business.model.person.Teacher;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "loan")
@Getter
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne
    private PresenceBox presenceBox;

    @Column(name = "start", nullable = false)
    private LocalDateTime start;
}
