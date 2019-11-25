package com.esta.assignment.controllers;

import com.esta.assignment.models.Employee;
import com.esta.assignment.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Employee Controller.
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController extends AbstractRestHandler {

    @Autowired
    private EmployeeService service;

    /**
     * Get All Employees.
     * @param page Integer
     * @param size Integer
     * @return List<Employee> All employees.
     */
    @GetMapping(produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public Page<Employee> getAllEmployees(@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                          @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        return service.getAllEmployee(page, size);
    }

    /**
     * Get an Employee by id.
     * @param id Integer
     * @return Employee details
     */
    @GetMapping(path = "/{id}",
            produces = {"application/json", "application/xml"})
    public Employee getEmployeeById(@PathVariable Long id) {
        Employee employee = service.getEmployeeById(id);
        return employee;
    }

    /**
     * Create an Employee.
     * @param employee Employee
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @PostMapping(consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createAnEmployee(@RequestBody Employee employee,
                                 HttpServletRequest request, HttpServletResponse response) {
        Employee createdEmployee = service.saveEmployee(employee);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdEmployee.getId()).toString());
    }

    /**
     * Update an existing Employee.
     * @param employee Employee
     * @param id Long
     */
    @PutMapping(path = "/{id}",
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAnEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        service.updateEmployee(employee, id);
    }

    /**
     * Delete an existing Employee
     * @param id Long
     */
    @DeleteMapping(path = "/{id}",
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
    }
}
