package com.esta.assignment.services;

import com.esta.assignment.exceptions.ResourceNotFoundException;
import com.esta.assignment.models.Employee;
import com.esta.assignment.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Employee service.
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    /**
     * Fetch all employees from the repository.
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
     * @param employeeId Long
     * @return Employee details.
     */
    public Employee getEmployeeById(Long employeeId) {
        return repository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee %s is couldn't found!", employeeId)));
    }

    /**
     * Create an Employee.
     * @param employee Employee
     * @return Employee details.
     */
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    /**
     * Update an existing Employee.
     * @param employee Employee
     * @param id Long
     */
    public void updateEmployee(Employee employee, Long id) {
        Employee exitsEmployee = getEmployeeById(id);
        employee.setId(exitsEmployee.getId());
        repository.save(employee);
    }

    /**
     * Delete an existing Employee
     * @param id Long
     */
    public void deleteEmployee(Long id) {
        this.getEmployeeById(id);
        repository.deleteById(id);
    }
}
