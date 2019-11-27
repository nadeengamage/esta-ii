package com.esta.assignment.listeners;

import com.esta.assignment.models.Assigner;
import com.esta.assignment.models.audits.AssignerHistory;
import com.esta.assignment.services.UtilityService;

import javax.persistence.EntityManager;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;
import java.sql.Timestamp;

/**
 * Assigner history Event listener.
 */
public class AssignerEntityListener {

    @PreUpdate
    public void postUpdate(Assigner assigner) {
        perform(new AssignerHistory(
                assigner, "Updated an Assigner", "UPDATED"
        ));
    }

    @PreRemove
    public void postRemove(Assigner assigner) {
        perform(new AssignerHistory(
                assigner, "Deleted an Assigner", "DELETED"
        ));
    }

    @Transactional(Transactional.TxType.MANDATORY)
    public void perform(AssignerHistory history) {
        java.util.Date date = new java.util.Date();
        history.setHistoryDate(new Timestamp(date.getTime()));
        EntityManager manager = UtilityService.getBean(EntityManager.class);
        manager.persist(history);
    }
}
