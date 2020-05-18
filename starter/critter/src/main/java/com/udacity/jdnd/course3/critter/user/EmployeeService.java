package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee){
       return employeeRepository.saveAndFlush(employee);


    }

    public Employee getEmployee(long id){
        return employeeRepository.findEmployeeById(id);
    }
}
