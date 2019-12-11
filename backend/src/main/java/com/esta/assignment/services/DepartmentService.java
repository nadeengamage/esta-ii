package com.esta.assignment.services;

import com.esta.assignment.exceptions.ResourceNotFoundException;
import com.esta.assignment.models.Department;
import com.esta.assignment.models.audits.DepartmentHistory;
import com.esta.assignment.repositories.DepartmentRepository;
import com.esta.assignment.repositories.audit.DepartmentHistoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Department service.
 */
@Service
public class DepartmentService {

    /**
     * Logger
     */
    private final static Logger log = LogManager.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private DepartmentHistoryRepository historyRepository;

    /**
     * Fetch all department from the repository.
     *
     * @param page Integer
     * @param size Integer
     * @return List<Department> All department.
     */
    public Page<Department> getAllDepartment(Integer page, Integer size) {
        log.debug("Start fetching the departments");
        Pageable pageable = PageRequest.of(page, size);
        Page<Department> pageOfDepartments = repository.findAll(pageable);
        log.debug("Fetched the departments");
        return pageOfDepartments;
    }

    /**
     * Get an existing Department.
     *
     * @param departmentId Long
     * @return Department details.
     */
    public Department getDepartmentById(String departmentId) {
        log.debug("Start the search an department by id");
        return repository.findByIdentityNo(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Department %s is couldn't found!", departmentId)));
    }

    /**
     * Create an Department.
     *
     * @param department Department
     * @return Department details.
     */
    public Department saveDepartment(Department department) {
        log.debug("Start create an object of department");
        Date date = new Date();
        Department createdDepartment = repository.saveAndFlush(department);
        DepartmentHistory departmentHistory = new DepartmentHistory(createdDepartment, "Inserted an Department", "CREATED");
        departmentHistory.setHistoryDate(new Timestamp(date.getTime()));
        historyRepository.save(departmentHistory);
        log.debug("Saved create of department object");
        return createdDepartment;
    }

    /**
     * Update an existing Department.
     *
     * @param department Department
     * @param id         Long
     */
    public void updateDepartment(Department department, String id) {
        log.debug("Start update an object of department");
        Department exitsDepartment = getDepartmentById(id);
        department.setId(exitsDepartment.getId());
        repository.save(department);
        log.debug("Saved of department object");
    }

    /**
     * Delete an existing Department
     *
     * @param id Long
     */
    public void deleteDepartment(String id) {
        log.debug("Start delete of department object");
        Department exitsDepartment = getDepartmentById(id);
        repository.delete(exitsDepartment);
        log.debug("Deleted of department object");
    }
}
