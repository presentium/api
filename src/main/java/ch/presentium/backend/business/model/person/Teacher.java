package ch.presentium.backend.business.model.person;

import ch.presentium.backend.business.model.academic.Class;
import ch.presentium.backend.business.model.device.Loan;
import ch.presentium.backend.business.model.device.PresenceBox;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "teacher")
@Getter
public class Teacher extends Person {
    @OneToMany(mappedBy = "teacher")
    private List<Class> classes;
}
