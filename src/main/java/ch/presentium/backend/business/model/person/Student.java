package ch.presentium.backend.business.model.person;

import ch.presentium.backend.business.model.academic.Course;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "student")
@Getter
public class Student extends Person {
    @ManyToMany
    private List<Course> courses;
}
