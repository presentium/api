package ch.presentium.backend.business.model.device;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "presence_box")
@Getter
public class PresenceBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
