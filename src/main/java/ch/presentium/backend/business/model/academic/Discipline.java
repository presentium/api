package ch.presentium.backend.business.model.academic;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "discipline")
@Getter
public class Discipline {
    @Id
    private String id;
    private String description;
    private Integer duration;
    @OneToMany
    private List<Course> courses;
}
