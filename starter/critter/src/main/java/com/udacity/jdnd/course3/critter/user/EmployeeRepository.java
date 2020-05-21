package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    public Employee findEmployeeById(long id);

    public List<Employee> findAllByWeekDaysNameContains(String name);
}
