package ch.presentium.backend.business.model;

import ch.presentium.backend.business.model.schedule.ClassSession;
import ch.presentium.backend.business.model.user.Student;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "presence")
@Getter
@Setter
@Accessors(chain = true)
public class Presence {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false)
    @JoinColumn(name = "student_fk", foreignKey = @ForeignKey(name = "fk_student"), nullable = false)
    private Student student;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false)
    @JoinColumn(name = "class_session_fk", foreignKey = @ForeignKey(name = "fk_presence_session"), nullable = false)
    private ClassSession classSession;
}
