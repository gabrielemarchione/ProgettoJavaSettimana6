package gabrielemarchione.progettoJavaSettimana6.repositories;

import gabrielemarchione.progettoJavaSettimana6.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    boolean existsByEmail(String email);
}
