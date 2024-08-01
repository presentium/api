package ch.presentium.backend.business.model.device;

import ch.presentium.backend.business.model.person.Teacher;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "returned_loan")
@Getter
public class ReturnedLoan {
    @Id
    private Long id;
    @ManyToOne
    private Teacher teacher;
    @ManyToOne
    private PresenceBox attendanceBox;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
