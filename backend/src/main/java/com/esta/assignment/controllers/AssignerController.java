package com.esta.assignment.controllers;

import com.esta.assignment.models.Assigner;
import com.esta.assignment.models.audits.AssignerHistory;
import com.esta.assignment.services.AssignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Assigner Controller.
 */
@RestController
@RequestMapping("/assigners")
public class AssignerController extends AbstractRestHandler {

    @Autowired
    private AssignerService service;

    /**
     * Get All Assigners.
     *
     * @param page Integer
     * @param size Integer
     * @return List<Assigner> All assigners.
     */
    @GetMapping(produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Page<Assigner> getAllAssigners(@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                          @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        return service.getAllAssigner(page, size);
    }

    /**
     * Get All Assigners.
     *
     * @param page Integer
     * @param size Integer
     * @return List<Assigner> All assigners.
     */
    @GetMapping(path = "history", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Page<AssignerHistory> getAllAssignerHistory(@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                                       @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        return service.getAllAssignerHistory(page, size);
    }

    /**
     * Get an Assigner by id.
     *
     * @param id Integer
     * @return Assigner details
     */
    @GetMapping(path = "/{id}",
            produces = {"application/json"})
    public Assigner getAssignerById(@PathVariable Long id) {
        Assigner department = service.getAssignerById(id);
        return department;
    }

    /**
     * Create an Assigner.
     *
     * @param department Assigner
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     */
    @PostMapping(consumes = {"application/json"},
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createAnAssigner(@RequestBody Assigner department,
                                 HttpServletRequest request, HttpServletResponse response) {
        Assigner createdAssigner = service.saveAssigner(department);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdAssigner.getId()).toString());
    }

    /**
     * Update an existing Assigner.
     *
     * @param department Assigner
     * @param id         Long
     */
    @PutMapping(path = "/{id}",
            consumes = {"application/json"},
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAnAssigner(@RequestBody Assigner department, @PathVariable Long id) {
        service.updateAssigner(department, id);
    }

    /**
     * Delete an existing Assigner
     *
     * @param id Long
     */
    @DeleteMapping(path = "/{id}",
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnAssigner(@PathVariable Long id) {
        service.deleteAssigner(id);
    }
}
