package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PetService petService;


    @PostMapping("/customer")
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer/pet/{petId}")
    public Customer getOwnerByPet(@PathVariable long petId){
       return petService.getPetById(petId).getOwner();
    }

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        System.out.println(employee.getName());
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/employee/{employeeId}")
    public Employee getEmployee(@PathVariable long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody String daysAvailable, @PathVariable long employeeId) {
      Employee employee = employeeService.getEmployee(employeeId);
      employeeService.saveEmployee(employee);
    }

    @GetMapping("/employee/availability")
    public List<Employee> findEmployeesForService() {
        return employeeService.getAllEmployee();

    }



}
