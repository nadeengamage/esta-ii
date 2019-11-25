package com.esta.assignment.listeners;

import com.esta.assignment.models.Employee;
import com.esta.assignment.models.audits.EmployeeHistory;
import com.esta.assignment.services.UtilityService;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;
import java.sql.Timestamp;

/**
 * Employee history Event listener.
 */
public class EmployeeEntityListener {

    @PrePersist
    public void prePersist(Employee employee) {
        perform(new EmployeeHistory(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getWorkingHours(),
                employee.getDateJoin(),
                employee.getDateLeft(),
                employee.getStatus(),
                "Inserted an Employee",
                "+"
        ));
    }

    @PreUpdate
    public void preUpdate(Employee employee) {
        perform(new EmployeeHistory(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getWorkingHours(),
                employee.getDateJoin(),
                employee.getDateLeft(),
                employee.getStatus(),
                "Updated an Employee",
                "+"
        ));
    }

    @PreRemove
    public void preRemove(Employee employee) {
        perform(new EmployeeHistory(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getWorkingHours(),
                employee.getDateJoin(),
                employee.getDateLeft(),
                employee.getStatus(),
                "Deleted an Employee",
                "-"
        ));
    }

    @Transactional
    public void perform(EmployeeHistory history) {
        java.util.Date date = new java.util.Date();
        history.setHistoryDate(new Timestamp(date.getTime()));
        EntityManager manager = UtilityService.getBean(EntityManager.class);
        manager.persist(history);
    }
}
