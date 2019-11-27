package com.esta.assignment.listeners;

import com.esta.assignment.models.Department;
import com.esta.assignment.models.audits.DepartmentHistory;
import com.esta.assignment.services.UtilityService;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;
import java.sql.Timestamp;

/**
 * Department history Event listener.
 */
public class DepartmentEntityListener {

    @PreUpdate
    public void preUpdate(Department department) {
        perform(new DepartmentHistory(
                department, "Updated an Department", "UPDATED"
        ));
    }

    @PreRemove
    public void preRemove(Department department) {
        perform(new DepartmentHistory(
                department, "Deleted an Department", "DELETED"
        ));
    }

    @Transactional(Transactional.TxType.MANDATORY)
    public void perform(DepartmentHistory history) {
        java.util.Date date = new java.util.Date();
        history.setHistoryDate(new Timestamp(date.getTime()));
        EntityManager manager = UtilityService.getBean(EntityManager.class);
        manager.persist(history);
    }
}
