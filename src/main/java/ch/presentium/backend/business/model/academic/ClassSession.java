package ch.presentium.backend.business.model.academic;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "class_session")
@Getter
@Setter
public class ClassSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "week", nullable = false)
    private Long week;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
