package com.esta.assignment.models;

import com.esta.assignment.listeners.AssignerEntityListener;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Time;

@Table(name = "assigner")
@Entity
@EntityListeners(AssignerEntityListener.class)
public class Assigner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "working_hours")
    private Time workingHours;

    @ManyToOne
    @JoinColumn(name = "dep_id", referencedColumnName = "dep_id", foreignKey = @ForeignKey(name = "assigner_department_fk"))
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id", foreignKey = @ForeignKey(name = "assigner_employee_fk"))
    @OnDelete(action = OnDeleteAction.NO_ACTION)
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

    @PrePersist
    public void onPrePersist() { }

    @PreUpdate
    public void onPreUpdate() { }

    @PreRemove
    public void onPreRemove() { }
}
