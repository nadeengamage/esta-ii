package com.esta.assignment.dtos;

import java.io.Serializable;
import java.sql.Time;

public class DepartmentDTO implements Serializable {

    private Long id;

    private String name;

    private Integer workingDaysPerWeek;

    private Time workingHoursPerDay;

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
