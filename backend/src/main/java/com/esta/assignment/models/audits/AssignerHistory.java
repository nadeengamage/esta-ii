package com.esta.assignment.models.audits;

import com.esta.assignment.models.Assigner;
import com.esta.assignment.models.Department;
import com.esta.assignment.models.Employee;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "assigner_audit_trail")
@EntityListeners(AuditingEntityListener.class)
public class AssignerHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    @Column(name = "assigner_id")
    private Long assignerId;

    @Column(name = "working_hours")
    private Time workingHours;

    @ManyToOne
    @JoinColumn(name = "dep_id", referencedColumnName = "dep_id", foreignKey = @ForeignKey(name = "assigner_department_audit_trail_fk"))
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id", foreignKey = @ForeignKey(name = "assigner_employee_audit_trail_fk"))
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Employee employee;

    @Column(name = "history_date")
    private Timestamp historyDate;

    @Column(name = "history_change_reason")
    private String historyChangeReason;

    @Column(name = "history_type")
    private String historyType;

    public AssignerHistory(Assigner assigner, String historyChangeReason, String historyType) {
        this.assignerId = assigner.getId();
        this.workingHours = assigner.getWorkingHours();
        this.department = assigner.getDepartment();
        this.employee = assigner.getEmployee();
        this.historyChangeReason = historyChangeReason;
        this.historyType = historyType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(Long assignerId) {
        this.assignerId = assignerId;
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
