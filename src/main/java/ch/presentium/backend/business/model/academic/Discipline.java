package ch.presentium.backend.business.model.academic;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "discipline")
@Getter
public class Discipline {
    @Id
    private String id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @OneToMany
    @JoinColumn(name = "discipline_id")
    private List<Course> courses;
}
