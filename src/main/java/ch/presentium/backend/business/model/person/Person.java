package ch.presentium.backend.business.model.person;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
public abstract class Person {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
