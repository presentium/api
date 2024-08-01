package ch.presentium.backend.business.model.device;

import ch.presentium.backend.business.model.academic.ClassSession;
import ch.presentium.backend.business.model.person.Student;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "presence")
@Getter
public class Presence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Student student;
    @ManyToOne
    private ClassSession classSession;
}
