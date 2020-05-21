package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class WeekService {
    @Autowired
    WeekRepository weekRepository;

    public Week getWeek(long id) {
        return weekRepository.findWeekById(id);
    }

    public Week saveWeek(Week week) {

        return weekRepository.saveAndFlush(week);
    }

    public List<Week> getallWeek(){
        return weekRepository.findAll();
    }
}
