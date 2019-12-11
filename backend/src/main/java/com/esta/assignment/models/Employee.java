package com.esta.assignment.models;

import com.esta.assignment.listeners.EmployeeEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Entity
@Table(name = "employee")
@EntityListeners(EmployeeEntityListener.class)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;

    @Column(name = "identity_no", unique = true, nullable = false, updatable = false)
    private String identityNo = UUID.randomUUID().toString();

    @Column(name = "first_name")
    @NotBlank(message = "First Name is required!")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last Name is required!")
    private String lastName;

    @Column(name = "working_hours")
    @NotNull(message = "Working hours is required!")
    private Time workingHours;

    @Column(name = "date_join")
    @NotNull(message = "Date of join is required!")
    private Date dateJoin;

    @Column(name = "date_left")
    private Date dateLeft;

    @Column(name = "status")
    @NotNull(message = "Employee status is required!")
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Time getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Time workingHours) {
        this.workingHours = workingHours;
    }

    public Date getDateJoin() {
        return dateJoin;
    }

    public void setDateJoin(Date dateJoin) {
        this.dateJoin = dateJoin;
    }

    public Date getDateLeft() {
        return dateLeft;
    }

    public void setDateLeft(Date dateLeft) {
        this.dateLeft = dateLeft;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
