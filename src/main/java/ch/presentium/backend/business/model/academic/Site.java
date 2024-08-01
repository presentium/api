package ch.presentium.backend.business.model.academic;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "site")
@Getter
public class Site {
    @Id
    private String id;
    private String name;
    private String address;
    private String city;
    private String zipCode;
}
