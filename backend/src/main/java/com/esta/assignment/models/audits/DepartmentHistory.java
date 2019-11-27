package com.esta.assignment.models.audits;

import com.esta.assignment.models.Department;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "department_audit_trail")
public class DepartmentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "history_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dep_id", foreignKey = @ForeignKey(name = "department_audit_trail_fk"))
    private Department department;

    @Column(name = "name")
    private String name;

    @Column(name = "working_days_per_week")
    private Integer workingDays;

    @Column(name = "working_hours_per_day")
    private Time workingHours;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "history_date")
    private Timestamp historyDate;

    @Column(name = "history_change_reason")
    private String historyReason;

    @Column(name = "history_type")
    private String historyType;

    public DepartmentHistory(Department department, String historyReason, String historyType) {
        this.department = department;
        this.name = department.getName();
        this.workingDays = department.getWorkingDaysPerWeek();
        this.workingHours = department.getWorkingHoursPerDay();
        this.status = department.getStatus();
        this.historyReason = historyReason;
        this.historyType = historyType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(Integer workingDays) {
        this.workingDays = workingDays;
    }

    public Time getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Time workingHours) {
        this.workingHours = workingHours;
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

    public String getHistoryReason() {
        return historyReason;
    }

    public void setHistoryReason(String historyReason) {
        this.historyReason = historyReason;
    }

    public String getHistoryType() {
        return historyType;
    }

    public void setHistoryType(String historyType) {
        this.historyType = historyType;
    }
}
