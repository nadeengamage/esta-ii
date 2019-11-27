package com.esta.assignment.repositories.audit;

import com.esta.assignment.models.audits.AssignerHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence operations of the AssignerHistory model.
 */
@Repository
public interface AssignerHistoryRepository extends JpaRepository<AssignerHistory, Long> {
}
