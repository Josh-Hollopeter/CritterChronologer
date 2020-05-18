package com.udacity.jdnd.course3.critter.user;

import javax.persistence.*;
import java.util.HashSet;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Transient
    private HashSet<EmployeeSkill> skills;

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
}
