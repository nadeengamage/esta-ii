package com.esta.assignment.dtos;

import com.esta.assignment.models.Department;
import com.esta.assignment.models.Employee;

import java.io.Serializable;
import java.sql.Time;

public class AssignerDTO implements Serializable {

    private String id;

    private Time workingHours;

    private DepartmentDTO department;

    private EmployeeDTO employee;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Time getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Time workingHours) {
        this.workingHours = workingHours;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }
}
