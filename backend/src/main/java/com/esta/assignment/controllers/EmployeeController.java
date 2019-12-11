package com.esta.assignment.controllers;

import com.esta.assignment.dtos.EmployeeDTO;
import com.esta.assignment.models.Employee;
import com.esta.assignment.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Employee Controller.
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController extends AbstractRestHandler {

    @Autowired
    private EmployeeService service;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get All Employees.
     *
     * @param page Integer
     * @param size Integer
     * @return List<Employee> All employees.
     */
    @GetMapping(produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Page<EmployeeDTO> getAllEmployees(@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                             @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        Page<EmployeeDTO> employeeDTOPage = new PageImpl<EmployeeDTO>(
                service.getAllEmployee(page, size)
                        .getContent()
                        .stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList()));
        return employeeDTOPage;
    }

    /**
     * Get an Employee by id.
     *
     * @param id Integer
     * @return Employee details
     */
    @GetMapping(path = "/{id}",
            produces = {"application/json"})
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employeeDTO = this.convertToDto(service.getEmployeeById(id));
        return employeeDTO;
    }

    /**
     * Create an Employee.
     *
     * @param employee Employee
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    @PostMapping(consumes = {"application/json"},
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createAnEmployee(@RequestBody Employee employee,
                                 HttpServletRequest request, HttpServletResponse response) {
        Employee createdEmployee = service.saveEmployee(employee);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdEmployee.getId()).toString());
    }

    /**
     * Update an existing Employee.
     *
     * @param employee Employee
     * @param id       Long
     */
    @PutMapping(path = "/{id}",
            consumes = {"application/json"},
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAnEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        service.updateEmployee(employee, id);
    }

    /**
     * Delete an existing Employee
     *
     * @param id Long
     */
    @DeleteMapping(path = "/{id}",
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
    }

    /**
     * Convert to DTO
     * @param employee
     * @return employee DTO
     */
    private EmployeeDTO convertToDto(Employee employee) {
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        employeeDTO.setId(employee.getIdentityNo());
        return employeeDTO;
    }

}
