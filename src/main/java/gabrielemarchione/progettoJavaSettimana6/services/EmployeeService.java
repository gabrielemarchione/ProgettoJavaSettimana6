package gabrielemarchione.progettoJavaSettimana6.services;

import gabrielemarchione.progettoJavaSettimana6.dto.EmployeeDTO;
import gabrielemarchione.progettoJavaSettimana6.entities.Booking;
import gabrielemarchione.progettoJavaSettimana6.entities.Employee;
import gabrielemarchione.progettoJavaSettimana6.exceptions.BadRequestException;
import gabrielemarchione.progettoJavaSettimana6.exceptions.NotFoundException;
import gabrielemarchione.progettoJavaSettimana6.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }
    public Employee findById(UUID employeeId) {
        return this.employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException(employeeId));
    }
    public Employee saveEmployee(EmployeeDTO payload) {
        if (this.employeeRepository.existsByEmail(payload.email()))
            throw new BadRequestException("La mail è già in uso");
        Employee newEmployee = new Employee(payload.name(), payload.surname(), payload.email(), payload.username());
        return this.employeeRepository.save(newEmployee);
    }
    public Employee findByIdAndUpdate(UUID employeeId, EmployeeDTO payload) {
        Employee Employee = this.findById(employeeId);
        if (!Employee.getEmail().equals(payload.email())) {
            if (this.employeeRepository.existsByEmail(payload.email()))
                throw new BadRequestException("La mail è già in uso");
        }
        Employee.setUsername(payload.username());
        Employee.setName(payload.name());
        Employee.setSurname(payload.surname());
        Employee.setEmail(payload.email());
        return this.employeeRepository.save(Employee);
    }
    public void deleteEmployee(UUID employeeId) {
        Employee Employee = this.findById(employeeId);
        this.employeeRepository.delete(Employee);
    }
    public List<Booking> findBookingsByEmployeeId(UUID employeeId) {
        Employee employee = this.findById(employeeId);
        return employee.getBookingList();
    }
}
