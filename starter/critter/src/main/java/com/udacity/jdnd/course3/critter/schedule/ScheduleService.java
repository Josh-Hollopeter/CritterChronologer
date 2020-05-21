package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule saveSchedule(Schedule schedule){
      return  scheduleRepository.saveAndFlush(schedule);

    }

    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleByPet(long id){
        return scheduleRepository.findAllByPetId(id);
    }

    public List<Schedule> getScheduleByEmployee(long id){
        return scheduleRepository.findAllByEmployeeId(id);
    }

    public List<Schedule> getScheduleByCustomer(long id){
        return scheduleRepository.findAllByCustomerId(id);
    }
}
