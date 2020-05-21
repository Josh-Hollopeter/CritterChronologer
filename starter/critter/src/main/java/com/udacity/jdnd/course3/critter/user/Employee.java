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


    @Column(name="availability")
    private String daysAvailable;

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

    public Set<String> getDaysAvailable() {
        return (null == daysAvailable) ?
                Collections.emptySet() :
                Arrays.stream(daysAvailable.split(",")).map(String::valueOf).collect(Collectors.toSet());
    }


    public void setDaysAvailable(String daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
