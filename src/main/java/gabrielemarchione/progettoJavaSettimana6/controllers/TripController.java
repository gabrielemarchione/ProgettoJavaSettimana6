package gabrielemarchione.progettoJavaSettimana6.controllers;

import gabrielemarchione.progettoJavaSettimana6.dto.TripDTO;
import gabrielemarchione.progettoJavaSettimana6.dto.TripStatusDTO;
import gabrielemarchione.progettoJavaSettimana6.entities.Trip;
import gabrielemarchione.progettoJavaSettimana6.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Trip> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "destination") String sortBy) {
        return this.tripService.findAll(page, size, sortBy);
    }

    @GetMapping("/{trip_id}")
    @ResponseStatus(HttpStatus.OK)
    public Trip getTrip(@PathVariable UUID trip_id) {
        return this.tripService.findById(trip_id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trip postTrip(@RequestBody TripDTO payload) {
        return this.tripService.saveTrip(payload);
    }

    @PutMapping("/{trip_id}")
    @ResponseStatus(HttpStatus.OK)
    public Trip putTrip(@PathVariable UUID trip_id, @RequestBody TripDTO payload) {
        return this.tripService.findByIdAndUpdate(trip_id, payload);
    }

    @DeleteMapping("/{trip_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrip(@PathVariable UUID trip_id) {
        this.tripService.deleteTrip(trip_id);
    }

    @PatchMapping("/{trip_id}/status")
    @ResponseStatus(HttpStatus.OK)
    public Trip updateTripStatus(@PathVariable UUID trip_id, @RequestBody TripStatusDTO payload) {
        return tripService.updateTripStatus(trip_id, payload);
    }
}