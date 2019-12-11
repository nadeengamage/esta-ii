package com.esta.assignment.repositories;

import com.esta.assignment.models.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Persistence operations of the Department model.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Page<Department> findAll(Pageable pageable);

    Optional<Department> findByIdentityNo(String identityNo);
}
