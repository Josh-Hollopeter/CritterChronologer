package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekRepository extends JpaRepository<Week,Long> {


    public Week findWeekById(long id);
}
