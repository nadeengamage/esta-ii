package com.esta.assignment.services;

import com.esta.assignment.exceptions.ResourceNotFoundException;
import com.esta.assignment.models.Department;
import com.esta.assignment.models.audits.DepartmentHistory;
import com.esta.assignment.repositories.DepartmentRepository;
import com.esta.assignment.repositories.audit.DepartmentHistoryRepository;
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

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private DepartmentHistoryRepository historyRepository;

    /**
     * Fetch all department from the repository.
     * @param page Integer
     * @param size Integer
     * @return List<Department> All department.
     */
    public Page<Department> getAllDepartment(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Department> pageOfDepartments = repository.findAll(pageable);
        return pageOfDepartments;
    }

    /**
     * Get an existing Department.
     * @param departmentId Long
     * @return Department details.
     */
    public Department getDepartmentById(Long departmentId) {
        return repository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Department %s is couldn't found!", departmentId)));
    }

    /**
     * Create an Department.
     * @param department Department
     * @return Department details.
     */
    public Department saveDepartment(Department department) {
        Date date = new Date();
        Department createdDepartment = repository.saveAndFlush(department);
        DepartmentHistory departmentHistory = new DepartmentHistory(createdDepartment, "Inserted an Department", "CREATED");
        departmentHistory.setHistoryDate(new Timestamp(date.getTime()));
        historyRepository.save(departmentHistory);
        return createdDepartment;
    }

    /**
     * Update an existing Department.
     * @param department Department
     * @param id Long
     */
    public void updateDepartment(Department department, Long id) {
        Department exitsDepartment = getDepartmentById(id);
        department.setId(exitsDepartment.getId());
        repository.save(department);
    }

    /**
     * Delete an existing Department
     * @param id Long
     */
    public void deleteDepartment(Long id) {
        Department exitsDepartment = getDepartmentById(id);
        repository.delete(exitsDepartment);
    }
}
