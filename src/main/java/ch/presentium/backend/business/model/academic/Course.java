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
    private Semester semester;
    private Integer year;
    @ManyToOne
    private Discipline discipline;
    @OneToMany
    private List<Class> classes;
    @ManyToMany
    private List<Student> students;
}
