package com.esta.assignment.repositories.audit;

import com.esta.assignment.models.audits.EmployeeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence operations of the EmployeeHistory model.
 */
@Repository
public interface EmployeeHistoryRepository extends JpaRepository<EmployeeHistory, Long> {
}
