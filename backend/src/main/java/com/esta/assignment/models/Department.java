package com.esta.assignment.models;

import com.esta.assignment.listeners.DepartmentEntityListener;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "department")
@EntityListeners(DepartmentEntityListener.class)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dep_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "working_days_per_week")
    private Integer workingDaysPerWeek;

    @Column(name = "working_hours_per_day")
    private Time workingHoursPerDay;

    @Column(name = "status")
    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWorkingDaysPerWeek() {
        return workingDaysPerWeek;
    }

    public void setWorkingDaysPerWeek(Integer workingDaysPerWeek) {
        this.workingDaysPerWeek = workingDaysPerWeek;
    }

    public Time getWorkingHoursPerDay() {
        return workingHoursPerDay;
    }

    public void setWorkingHoursPerDay(Time workingHoursPerDay) {
        this.workingHoursPerDay = workingHoursPerDay;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
