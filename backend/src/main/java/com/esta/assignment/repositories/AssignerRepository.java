package com.esta.assignment.repositories;

import com.esta.assignment.models.Assigner;
import com.esta.assignment.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

/**
 * Persistence operations of the Assigner model.
 */
@Repository
public interface AssignerRepository extends JpaRepository<Assigner, Long> {

    Page<Assigner> findAll(Pageable pageable);

    @Query("SELECT A.workingHours FROM Assigner A WHERE A.employee.identityNo = ?1")
    List<Time> findByEmployeeId(String employeeId);

    Optional<Assigner> findByIdentityNo(String identityNo);
}
