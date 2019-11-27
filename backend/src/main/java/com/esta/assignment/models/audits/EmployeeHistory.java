package com.esta.assignment.models.audits;

import com.esta.assignment.models.Employee;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "employee_audit_trail")
@EntityListeners(AuditingEntityListener.class)
public class EmployeeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "history_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "emp_id", foreignKey = @ForeignKey(name = "employee_audit_trail_fk"))
    private Employee employee;

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

    public EmployeeHistory(Employee employee, String historyChangeReason, String historyType) {
        this.employee= employee;
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.workingHours = employee.getWorkingHours();
        this.dateJoin = employee.getDateJoin();
        this.dateLeft = employee.getDateLeft();
        this.status = employee.getStatus();
        this.historyChangeReason = historyChangeReason;
        this.historyType = historyType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
