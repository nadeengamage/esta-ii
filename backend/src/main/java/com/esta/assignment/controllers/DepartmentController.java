package com.esta.assignment.controllers;

import com.esta.assignment.dtos.DepartmentDTO;
import com.esta.assignment.models.Department;
import com.esta.assignment.services.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

/**
 * Department Controller.
 */
@RestController
@RequestMapping("/departments")
public class DepartmentController extends AbstractRestHandler {

    @Autowired
    private DepartmentService service;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get All Departments.
     *
     * @param page Integer
     * @param size Integer
     * @return List<Department> All departments.
     */
    @GetMapping(produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Page<DepartmentDTO> getAllDepartments(@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                                 @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        Page<DepartmentDTO> departmentDTOPage = new PageImpl<DepartmentDTO>(
                service.getAllDepartment(page, size)
                .getContent()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList())
        );

        return departmentDTOPage;
    }

    /**
     * Get an Department by id.
     *
     * @param id Integer
     * @return Department details
     */
    @GetMapping(path = "/{id}",
            produces = {"application/json"})
    public DepartmentDTO getDepartmentById(@PathVariable Long id) {
        DepartmentDTO departmentDTO = this.convertToDto(service.getDepartmentById(id));
        return departmentDTO;
    }

    /**
     * Create an Department.
     *
     * @param department Department
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     */
    @PostMapping(consumes = {"application/json"},
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createAnDepartment(@RequestBody Department department,
                                   HttpServletRequest request, HttpServletResponse response) {
        Department createdDepartment = service.saveDepartment(department);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdDepartment.getId()).toString());
    }

    /**
     * Update an existing Department.
     *
     * @param department Department
     * @param id         Long
     */
    @PutMapping(path = "/{id}",
            consumes = {"application/json"},
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAnDepartment(@RequestBody Department department, @PathVariable Long id) {
        service.updateDepartment(department, id);
    }

    /**
     * Delete an existing Department
     *
     * @param id Long
     */
    @DeleteMapping(path = "/{id}",
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnDepartment(@PathVariable Long id) {
        service.deleteDepartment(id);
    }

    /**
     * Convert to DTO
     * @param department
     * @return department DTO
     */
    private DepartmentDTO convertToDto(Department department) {
        DepartmentDTO departmentDTO = modelMapper.map(department, DepartmentDTO.class);
        departmentDTO.setId(department.getIdentityNo());
        return departmentDTO;
    }

}
