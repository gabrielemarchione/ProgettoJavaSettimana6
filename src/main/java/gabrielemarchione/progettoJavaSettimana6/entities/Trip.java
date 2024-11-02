package gabrielemarchione.progettoJavaSettimana6.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gabrielemarchione.progettoJavaSettimana6.enums.TripStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter

@NoArgsConstructor

@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue
    @Column(name = "trip_id")
    @Setter(AccessLevel.NONE)
    private UUID tripId;
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TripStatus status;
    @OneToOne(mappedBy = "trip", cascade = CascadeType.REMOVE)
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private Booking booking;

    public Trip(String destination) {
        this.destination = destination;
        this.status = TripStatus.IN_PROGRAM;
    }

    @Override
    public String toString() {
        return "Trip = ID:" + tripId +
                ", destination: " + destination +
                ", date: " + date +
                ", status: " + status;
    }
}