package com.esta.assignment.dtos;

import com.esta.assignment.models.Department;
import com.esta.assignment.models.Employee;

import java.io.Serializable;
import java.sql.Time;

public class AssignerDTO implements Serializable {

    private Long id;

    private Time workingHours;

    private Department department;

    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Time workingHours) {
        this.workingHours = workingHours;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
