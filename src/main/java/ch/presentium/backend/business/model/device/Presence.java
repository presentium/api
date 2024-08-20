package ch.presentium.backend.business.model.device;

import ch.presentium.backend.business.model.academic.ClassSession;
import ch.presentium.backend.business.model.person.Student;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "presence")
@Getter
public class Presence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "class_session_id", nullable = false)
    private ClassSession classSession;
}
