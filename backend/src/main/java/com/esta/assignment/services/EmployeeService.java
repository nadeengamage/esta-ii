package com.esta.assignment.services;

import com.esta.assignment.exceptions.ResourceNotFoundException;
import com.esta.assignment.models.Employee;
import com.esta.assignment.models.audits.EmployeeHistory;
import com.esta.assignment.repositories.EmployeeRepository;
import com.esta.assignment.repositories.audit.EmployeeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Employee service.
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EmployeeHistoryRepository historyRepository;

    /**
     * Fetch all employees from the repository.
     *
     * @param page Integer
     * @param size Integer
     * @return List<Employee> All employees.
     */
    public Page<Employee> getAllEmployee(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> pageOfEmployees = repository.findAll(pageable);
        return pageOfEmployees;
    }

    /**
     * Get an existing Employee.
     *
     * @param employeeId Long
     * @return Employee details.
     */
    public Employee getEmployeeById(Long employeeId) {
        return repository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee %s is couldn't found!", employeeId)));
    }

    /**
     * Create an Employee.
     *
     * @param employee Employee
     * @return Employee details.
     */
    public Employee saveEmployee(Employee employee) {
        Employee createdEmployee = repository.saveAndFlush(employee);
        EmployeeHistory history = new EmployeeHistory(createdEmployee, "Inserted an Employee", "CREATED");
        java.util.Date date = new java.util.Date();
        history.setHistoryDate(new Timestamp(date.getTime()));
        historyRepository.save(history);
        return createdEmployee;
    }

    /**
     * Update an existing Employee.
     *
     * @param employee Employee
     * @param id       Long
     */
    public void updateEmployee(Employee employee, Long id) {
        Employee exitsEmployee = getEmployeeById(id);
        employee.setId(exitsEmployee.getId());
        repository.save(employee);
    }

    /**
     * Delete an existing Employee
     *
     * @param id Long
     */
    public void deleteEmployee(Long id) {
        Employee exitsEmployee = getEmployeeById(id);
        repository.delete(exitsEmployee);
    }
}
