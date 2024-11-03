package gabrielemarchione.progettoJavaSettimana6.controllers;

import gabrielemarchione.progettoJavaSettimana6.dto.BookingDTO;
import gabrielemarchione.progettoJavaSettimana6.entities.Booking;
import gabrielemarchione.progettoJavaSettimana6.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Booking> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "bookingDate") String sortBy) {
        return this.bookingService.findAll(page, size, sortBy);
    }

    @GetMapping("/{bookingId}")
    @ResponseStatus(HttpStatus.OK)
    public Booking getBooking(@PathVariable UUID bookingId) {
        return this.bookingService.findById(bookingId);
    }

    @DeleteMapping("/{bookingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable UUID bookingId) {
        this.bookingService.findByIdAndDelete(bookingId);
    }

    @PostMapping("/employees/{employeeId}/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking saveBooking(
            @PathVariable UUID employeeId,
            @RequestBody @Validated BookingDTO payload
    ) {
        return this.bookingService.saveBooking(payload, employeeId);
    }

}
