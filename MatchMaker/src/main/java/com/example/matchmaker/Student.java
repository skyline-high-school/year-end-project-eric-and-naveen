package com.example.matchmaker;

import java.util.Formatter;
import java.util.Set;

enum Skill{
Artistic, Musical, Technological, Leader, Studious
}

public class Student {
    private Set desiredSkills;
    private Set skills;
    private int id;
    private String name;
    private String studentPreference;
    public Student(String name, int id, String studentPreference, Set skills, Set desiredSkills) {
        this.name = name;
        this.id = id;
        this.studentPreference = studentPreference;
        this.skills = skills;
        this.desiredSkills = desiredSkills;
    }
    public Set getDesiredSkills() {
        return desiredSkills;
    }

    public void setDesiredSkills(Set desiredSkills) {
        this.desiredSkills = desiredSkills;
    }

    public Set getSkills() {
        return skills;
    }

    public void setSkills(Set skills) {
        this.skills = skills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentPreference() {
        return studentPreference;
    }

    public void setStudentPreference(String studentPreference) {
        this.studentPreference = studentPreference;
    }
}
