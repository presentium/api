package ch.presentium.backend.business.model.device;

import ch.presentium.backend.business.model.person.Teacher;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "returned_loan")
@Getter
public class ReturnedLoan {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private PresenceBox attendanceBox;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;
}
