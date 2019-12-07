package com.esta.assignment.listeners;

import com.esta.assignment.models.Employee;
import com.esta.assignment.models.audits.EmployeeHistory;
import com.esta.assignment.services.UtilityService;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Timestamp;

/**
 * Employee history Event listener.
 */
public class EmployeeEntityListener {

    @PreUpdate
    public void postUpdate(Employee employee) {
        perform(new EmployeeHistory(
                employee, "Updated an Employee", "UPDATED"
        ));
    }

    @PreRemove
    public void postRemove(Employee employee) {
        perform(new EmployeeHistory(
                employee, "Deleted an Employee", "DELETED"
        ));
    }

    @Transactional(Transactional.TxType.MANDATORY)
    public void perform(EmployeeHistory history) {
        java.util.Date date = new java.util.Date();
        history.setHistoryDate(new Timestamp(date.getTime()));
        EntityManager manager = UtilityService.getBean(EntityManager.class);
        manager.persist(history);
    }
}
