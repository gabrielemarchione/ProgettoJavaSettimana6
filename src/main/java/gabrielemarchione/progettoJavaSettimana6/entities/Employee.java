package gabrielemarchione.progettoJavaSettimana6.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue
    @Column(name = "employee_id")
    @Setter(AccessLevel.NONE)
    private UUID employeeId;
    @Column(nullable = false)
    private String  name, surname, email, username;
    @Column(name = "avatar_url", nullable = false)
    private String avatarUrl;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    private List<Booking> bookingList;

    public Employee( String name, String surname, String email, String username) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.avatarUrl = "https://ui-avatars.com/api/?name=" + name + "+" + surname;
    }

    @Override
    public String toString() {
        return "Employee " + employeeId +
                ", name: " + name +
                ", surname: " + surname +
                ", email: " +email +
                " = username: " + username;
    }
}
