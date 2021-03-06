package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    public List<Schedule> findAllByPetId(long id);

    public List<Schedule> findAllByEmployeeId(long id);

    public List<Schedule> findAllByCustomerId(long id);
}
