package com.udacity.jdnd.course3.critter.user;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Transient
    private HashSet<EmployeeSkill> skills;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="week_has_employee")
    private Set<Week> weekDays;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashSet<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(HashSet<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<Week> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(Set<Week> week_days) {
        this.weekDays = weekDays;
    }

}
