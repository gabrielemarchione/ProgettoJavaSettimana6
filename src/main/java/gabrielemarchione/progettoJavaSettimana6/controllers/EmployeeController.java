package gabrielemarchione.progettoJavaSettimana6.controllers;

import gabrielemarchione.progettoJavaSettimana6.dto.BookingDTO;
import gabrielemarchione.progettoJavaSettimana6.dto.EmployeeDTO;
import gabrielemarchione.progettoJavaSettimana6.entities.Booking;
import gabrielemarchione.progettoJavaSettimana6.entities.Employee;
import gabrielemarchione.progettoJavaSettimana6.services.BookingService;
import gabrielemarchione.progettoJavaSettimana6.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
   private BookingService bookingService;


    @GetMapping
    public List<Employee> getEmployee() {
        return this.employeeService.findAll();
    }
    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable UUID employeeId) {
        return this.employeeService.findById(employeeId);
    }
    @PostMapping
    public Employee postEmployee(@RequestBody EmployeeDTO Employee) {
        return this.employeeService.saveEmployee(Employee);
    }
    @PutMapping("/{employeeId}")
    public Employee putEmployee(@PathVariable UUID employeeId, @RequestBody EmployeeDTO Employee) {
        return this.employeeService.findByIdAndUpdate(employeeId, Employee);
    }
    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable UUID employeeId) {
        this.employeeService.deleteEmployee(employeeId);
    }




    @GetMapping("/{employeeId}/bookings")
    @ResponseStatus(HttpStatus.OK)
    public List<Booking> getBookingsByEmployee(@PathVariable UUID employeeId) {
        List<Booking> bookingList = this.employeeService.findBookingsByEmployeeId(employeeId);
        return bookingList;
    }
    @PostMapping("/{employeeId}/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking postBooking(@RequestBody BookingDTO payload, @PathVariable UUID employeeId) {
        return this.bookingService.saveBooking(payload, employeeId);
    }

    @PutMapping("/{employeeId}/bookings/{bookingId}")
    @ResponseStatus(HttpStatus.OK)
    public Booking putBooking(@PathVariable UUID employeeId, @RequestBody BookingDTO payload, @PathVariable UUID bookingId) {
        return this.bookingService.findByIdAndUpdate(employeeId, payload, bookingId);
    }
}