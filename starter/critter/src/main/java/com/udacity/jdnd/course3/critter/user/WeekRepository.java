package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface WeekRepository extends JpaRepository<Week,Long> {


    public Week findWeekById(long id);

    public List<Week> findAllByOrderByIdAsc();

    public Week  findAllByName(String name);
}
