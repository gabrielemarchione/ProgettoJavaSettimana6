package gabrielemarchione.progettoJavaSettimana6.services;

import gabrielemarchione.progettoJavaSettimana6.dto.BookingDTO;
import gabrielemarchione.progettoJavaSettimana6.entities.Booking;
import gabrielemarchione.progettoJavaSettimana6.entities.Employee;
import gabrielemarchione.progettoJavaSettimana6.entities.Trip;
import gabrielemarchione.progettoJavaSettimana6.exceptions.NotFoundException;
import gabrielemarchione.progettoJavaSettimana6.repositories.BookingRepository;
import gabrielemarchione.progettoJavaSettimana6.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    TripService tripService;
    @Autowired
    TripRepository tripRepository;

    public Page<Booking> findAll(int page, int size, String sortBy) {
        if (size > 20) size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.bookingRepository.findAll(pageable);
    }

    public Booking findById(UUID bookingId) {
        return this.bookingRepository.findById(bookingId).orElseThrow(() -> new NotFoundException(bookingId));
    }

    public Booking saveBooking(BookingDTO payload, UUID employeeId) {
        Trip trip = this.tripRepository.findById(UUID.fromString(payload.trip_id()))
                .orElseThrow(() -> new NotFoundException(UUID.fromString(payload.trip_id())));
        Employee employee = this.employeeService.findById(employeeId);
        Booking booking = new Booking(trip, employee, payload.preferences());
        return this.bookingRepository.save(booking);
    }

    public void findByIdAndDelete(UUID bookingId) {
        Booking booking = findById(bookingId);
        bookingRepository.delete(booking);
    }
}

