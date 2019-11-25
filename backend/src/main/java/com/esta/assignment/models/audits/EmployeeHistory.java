package com.esta.assignment.models.audits;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "employee_audit_trail")
public class EmployeeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "history_id")
    private Long id;

    @Column(name = "emp_id")
    private Long employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "working_hours")
    private Time workingHours;

    @Column(name = "date_join")
    private Date dateJoin;

    @Column(name = "date_left")
    private Date dateLeft;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "history_date")
    private Timestamp historyDate;

    @Column(name = "history_change_reason")
    private String historyChangeReason;

    @Column(name = "history_type")
    private String historyType;

    public EmployeeHistory(Long employeeId, String firstName, String lastName, Time workingHours, Date dateJoin, Date dateLeft, Boolean status, String historyChangeReason, String historyType) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.workingHours = workingHours;
        this.dateJoin = dateJoin;
        this.dateLeft = dateLeft;
        this.status = status;
        this.historyChangeReason = historyChangeReason;
        this.historyType = historyType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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

    public Timestamp getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(Timestamp historyDate) {
        this.historyDate = historyDate;
    }

    public String getHistoryChangeReason() {
        return historyChangeReason;
    }

    public void setHistoryChangeReason(String historyChangeReason) {
        this.historyChangeReason = historyChangeReason;
    }

    public String getHistoryType() {
        return historyType;
    }

    public void setHistoryType(String historyType) {
        this.historyType = historyType;
    }

}
