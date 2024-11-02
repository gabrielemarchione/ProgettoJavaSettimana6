package gabrielemarchione.progettoJavaSettimana6.repositories;

import gabrielemarchione.progettoJavaSettimana6.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TripRepository extends JpaRepository<Trip, UUID> {
}
