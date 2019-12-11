package com.esta.assignment.models;

import com.esta.assignment.listeners.DepartmentEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.UUID;

@Entity
@Table(name = "department")
@EntityListeners(DepartmentEntityListener.class)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dep_id")
    private Long id;

    @Column(name = "identity_no", unique = true, nullable = false, updatable = false)
    private String identityNo = UUID.randomUUID().toString();

    @Column(name = "name")
    @NotBlank(message = "Department name is required!")
    private String name;

    @Column(name = "working_days_per_week")
    @NotNull(message = "Working days per week is required!")
    private Integer workingDaysPerWeek;

    @Column(name = "working_hours_per_day")
    @NotNull(message = "Working hours per day is required!")
    private Time workingHoursPerDay;

    @Column(name = "status")
    @NotNull(message = "Department status is required!")
    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
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
