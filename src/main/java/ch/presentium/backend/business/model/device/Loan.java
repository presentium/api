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
    private Teacher teacher;
    @ManyToMany
    private List<PresenceBox> presenceBoxes;
    private LocalDateTime start;
}
