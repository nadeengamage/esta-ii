package com.esta.assignment.repositories;

import com.esta.assignment.models.Assigner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence operations of the Assigner model.
 */
@Repository
public interface AssignerRepository extends JpaRepository<Assigner, Long> {
    Page<Assigner> findAll(Pageable pageable);
}
