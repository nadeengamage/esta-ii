package com.esta.assignment.services;

import com.esta.assignment.exceptions.ResourceNotFoundException;
import com.esta.assignment.models.Employee;
import com.esta.assignment.models.audits.EmployeeHistory;
import com.esta.assignment.repositories.EmployeeRepository;
import com.esta.assignment.repositories.audit.EmployeeHistoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    /**
     * Logger
     */
    private final static Logger log = LogManager.getLogger(EmployeeService.class);

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
        log.debug("Start fetching the employees");
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> pageOfEmployees = repository.findAll(pageable);
        log.debug("Fetched the employees");
        return pageOfEmployees;
    }

    /**
     * Get an existing Employee.
     *
     * @param employeeId Long
     * @return Employee details.
     */
    public Employee getEmployeeById(Long employeeId) {
        log.debug("Start the search an employee by id");
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
        log.debug("Start create an object of employee");
        Employee createdEmployee = repository.saveAndFlush(employee);
        EmployeeHistory history = new EmployeeHistory(createdEmployee, "Inserted an Employee", "CREATED");
        java.util.Date date = new java.util.Date();
        history.setHistoryDate(new Timestamp(date.getTime()));
        historyRepository.save(history);
        log.debug("Saved create of employee object");
        return createdEmployee;
    }

    /**
     * Update an existing Employee.
     *
     * @param employee Employee
     * @param id       Long
     */
    public void updateEmployee(Employee employee, Long id) {
        log.debug("Start update an object of employee");
        Employee exitsEmployee = getEmployeeById(id);
        employee.setId(exitsEmployee.getId());
        repository.save(employee);
        log.debug("Saved of employee object");
    }

    /**
     * Delete an existing Employee
     *
     * @param id Long
     */
    public void deleteEmployee(Long id) {
        log.debug("Start delete of employee object");
        Employee exitsEmployee = getEmployeeById(id);
        repository.delete(exitsEmployee);
        log.debug("Deleted of employee object");
    }
}
