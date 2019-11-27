package com.esta.assignment.services;

import com.esta.assignment.exceptions.ResourceNotFoundException;
import com.esta.assignment.models.Assigner;
import com.esta.assignment.models.audits.AssignerHistory;
import com.esta.assignment.repositories.AssignerRepository;
import com.esta.assignment.repositories.audit.AssignerHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Assigner service.
 */
@Service
public class AssignerService {

    @Autowired
    private AssignerRepository repository;

    @Autowired
    private AssignerHistoryRepository historyRepository;

    /**
     * Fetch all employees from the repository.
     * @param page Integer
     * @param size Integer
     * @return List<Assigner> All employees.
     */
    public Page<Assigner> getAllAssigner(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Assigner> pageOfAssigners = repository.findAll(pageable);
        return pageOfAssigners;
    }

    /**
     * Get an existing Assigner.
     * @param employeeId Long
     * @return Assigner details.
     */
    public Assigner getAssignerById(Long employeeId) {
        return repository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Assigner %s is couldn't found!", employeeId)));
    }

    /**
     * Create an Assigner.
     * @param employee Assigner
     * @return Assigner details.
     */
    public Assigner saveAssigner(Assigner employee) {
        Assigner createdAssigner = repository.saveAndFlush(employee);
        AssignerHistory history = new AssignerHistory(createdAssigner, "Inserted an Assigner", "CREATED");
        java.util.Date date = new java.util.Date();
        history.setHistoryDate(new Timestamp(date.getTime()));
        historyRepository.save(history);
        return createdAssigner;
    }

    /**
     * Update an existing Assigner.
     * @param employee Assigner
     * @param id Long
     */
    public void updateAssigner(Assigner employee, Long id) {
        Assigner exitsAssigner = getAssignerById(id);
        employee.setId(exitsAssigner.getId());
        repository.save(employee);
    }

    /**
     * Delete an existing Assigner
     * @param id Long
     */
    public void deleteAssigner(Long id) {
        Assigner exitsAssigner = getAssignerById(id);
        repository.save(exitsAssigner);
    }
}
