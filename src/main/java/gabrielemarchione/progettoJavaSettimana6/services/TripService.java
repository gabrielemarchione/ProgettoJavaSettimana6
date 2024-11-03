package gabrielemarchione.progettoJavaSettimana6.services;

import gabrielemarchione.progettoJavaSettimana6.dto.TripDTO;
import gabrielemarchione.progettoJavaSettimana6.dto.TripStatusDTO;
import gabrielemarchione.progettoJavaSettimana6.entities.Trip;
import gabrielemarchione.progettoJavaSettimana6.enums.TripStatus;
import gabrielemarchione.progettoJavaSettimana6.exceptions.NotFoundException;
import gabrielemarchione.progettoJavaSettimana6.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;

    public Page<Trip> findAll(int page, int size, String sortBy) {
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.tripRepository.findAll(pageable);
    }

    public Trip findById(UUID trip_id) {
        return this.tripRepository.findById(trip_id).orElseThrow(() -> new NotFoundException(trip_id));
    }

    public Trip saveTrip(TripDTO payload) {
        Trip newTrip = new Trip(payload.destination(), payload.data_andata(), payload.data_ritorno());
        return this.tripRepository.save(newTrip);
    }

    public Trip findByIdAndUpdate(UUID trip_id, TripDTO payload) {
        Trip trip = this.findById(trip_id);

        trip.setDestination(payload.destination());
        trip.setData_andata(payload.data_andata());
        trip.setData_ritorno(payload.data_ritorno());

        return this.tripRepository.save(trip);
    }

    public void deleteTrip(UUID trip_id) {
        Trip trip = this.findById(trip_id);
        this.tripRepository.delete(trip);
    }

    public Trip updateTripStatus(UUID trip_id, TripStatusDTO payload) {
        Trip trip = this.findById(trip_id);
        trip.setStatus(TripStatus.valueOf(payload.Trip_Status()));
        return tripRepository.save(trip);
    }
}
