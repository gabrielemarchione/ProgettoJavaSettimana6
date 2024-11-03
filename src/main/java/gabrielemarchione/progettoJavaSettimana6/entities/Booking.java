package gabrielemarchione.progettoJavaSettimana6.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    @Column(name = "booking_id")
    private UUID bookingId;
    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;
    @Column(nullable = false)
    private String preferences;

    @OneToOne
    @JoinColumn(name = "trip_id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Booking(Trip trip, Employee employee, String preferences) {
        this.trip = trip;
        this.employee = employee;
        this.bookingDate = LocalDate.now();
        this.preferences = preferences;
    }

    @Override
    public String toString() {
        return "Booking " + bookingId +
                " = (" + trip +
                "), (" + employee +
                "), preferences: " + (preferences == null ? "N/A" : preferences) +
                ", bookingDate: " + bookingDate;
    }
}
