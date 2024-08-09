package ch.presentium.backend.business.model.academic;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "room")
@Getter
public class Room {
    @EmbeddedId
    private RoomId id;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Embeddable
    public static class RoomId implements Serializable {
        @Column(name = "room_id")
        private String roomId;

        @Column(name = "site_id")
        private String siteId;

        public RoomId() {}

        public RoomId(String roomId, String siteId) {
            this.roomId = roomId;
            this.siteId = siteId;
        }


        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            RoomId roomId = (RoomId) obj;
            return this.roomId.equals(roomId.roomId) && this.siteId.equals(roomId.siteId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(roomId, siteId);
        }
    }

}
