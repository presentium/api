package ch.presentium.backend.business.model.schedule;

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
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * A session is a class that has been scheduled, it has a date and its owning course
 */
@Entity
@Table(name = "class_session")
@Getter
@Setter
@Accessors(chain = true)
public class ClassSession {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false)
    @JoinColumn(name = "school_class_fk", foreignKey = @ForeignKey(name = "fk_session_school_class"), nullable = false)
    private SchoolClass schoolClass;

    @Column(name = "dt_session", nullable = false)
    private LocalDateTime date;
}
