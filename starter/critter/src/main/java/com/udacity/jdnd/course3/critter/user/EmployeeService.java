package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    WeekRepository weekRepository;

    public Employee saveEmployee(Employee employee){
       return employeeRepository.saveAndFlush(employee);


    }

    public Employee getEmployee(long id){
        return employeeRepository.findEmployeeById(id);
    }

    public List<Employee> getAllEmployee (){
        return employeeRepository.findAll();
    }

    public Employee setDaysAvailable(Employee employee, TreeSet<Week> days) {
        List<Week> weekDays = weekRepository.findAllByOrderByIdAsc();
        Set<Week> empDays = new TreeSet<Week>();
        for (int i = 0; i < days.size(); i++) {
            empDays.add(weekDays.get(i));
        }
        employee.setWeekDays(empDays);
//        Collections.sort(days, new Comparator<Week>() {
//            @Override
//            public int compare(Week o1, Week o2) {
//                return (int) (o1.getId() - o2.getId());
//            }
//        });
        employeeRepository.saveAndFlush(employee);
        return employee;
    }


}
