package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public Schedule createSchedule(@RequestBody Schedule schedule) {
        return scheduleService.saveSchedule(schedule);
    }

    @GetMapping
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/pet/{petId}")
    public List<Schedule> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.getScheduleByPet(petId);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Schedule> getScheduleForEmployee(@PathVariable long employeeId) {
       return scheduleService.getScheduleByEmployee(employeeId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Schedule> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.getScheduleByCustomer(customerId);
    }
}
