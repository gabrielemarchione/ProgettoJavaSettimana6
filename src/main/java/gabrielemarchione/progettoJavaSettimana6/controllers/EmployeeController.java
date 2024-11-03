package gabrielemarchione.progettoJavaSettimana6.controllers;

import gabrielemarchione.progettoJavaSettimana6.dto.EmployeeDTO;
import gabrielemarchione.progettoJavaSettimana6.entities.Employee;
import gabrielemarchione.progettoJavaSettimana6.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService EmployeeService;
    @GetMapping
    public List<Employee> getEmployee() {
        return this.EmployeeService.findAll();
    }
    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable UUID employeeId) {
        return this.EmployeeService.findById(employeeId);
    }
    @PostMapping
    public Employee postEmployee(@RequestBody EmployeeDTO Employee) {
        return this.EmployeeService.saveEmployee(Employee);
    }
    @PutMapping("/{employeeId}")
    public Employee putEmployee(@PathVariable UUID employeeId, @RequestBody EmployeeDTO Employee) {
        return this.EmployeeService.findByIdAndUpdate(employeeId, Employee);
    }
    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable UUID employeeId) {
        this.EmployeeService.deleteEmployee(employeeId);
    }
}