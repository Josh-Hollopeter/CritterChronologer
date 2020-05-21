package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeekRepository extends JpaRepository<Week,Long> {


    public Week findWeekById(long id);

    public List<Week> findAllByOrderByIdAsc();
}
