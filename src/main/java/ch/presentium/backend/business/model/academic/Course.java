package ch.presentium.backend.business.model.academic;

import ch.presentium.backend.business.model.person.Student;
import ch.presentium.backend.utils.Semester;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "course")
@Getter
public class Course {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "semester", nullable = false)
    private Semester semester;

    @Column(name = "year", nullable = false)
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = false)
    private Discipline discipline;

    @OneToMany
    @JoinColumn(name = "course_id")
    private List<Class> classes;

    @ManyToMany
    private List<Student> students;
}
