package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/week")
public class WeekController {

    @Autowired
    private WeekService weekService;


    @GetMapping("/{weekId}")
    public Week getWeek(@PathVariable long weekId) {
        return weekService.getWeek(weekId);
    }

    @PostMapping("/save")
    public Week saveWeek(@RequestBody Week week){
        return weekService.saveWeek(week);
    }

    @GetMapping()
    public List<Week> getAllWeek() {
        return weekService.getallWeek();
    }
}
