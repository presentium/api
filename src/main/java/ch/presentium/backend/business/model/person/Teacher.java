package ch.presentium.backend.business.model.person;

import ch.presentium.backend.business.model.academic.Class;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "teacher")
@Getter
public class Teacher extends Person {
    @OneToMany
    private List<Class> classes;
}
