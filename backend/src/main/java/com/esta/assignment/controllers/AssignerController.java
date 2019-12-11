package com.esta.assignment.controllers;

import com.esta.assignment.dtos.AssignerDTO;
import com.esta.assignment.dtos.AssignerHistoryDTO;
import com.esta.assignment.models.Assigner;
import com.esta.assignment.models.audits.AssignerHistory;
import com.esta.assignment.services.AssignerService;
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
 * Assigner Controller.
 */
@RestController
@RequestMapping("/assigners")
public class AssignerController extends AbstractRestHandler {

    @Autowired
    private AssignerService service;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get All Assigners.
     *
     * @param page Integer
     * @param size Integer
     * @return List<Assigner> All assigners.
     */
    @GetMapping(produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Page<AssignerDTO> getAllAssigners(@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                          @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        Page<AssignerDTO> assignerDTOPage = new PageImpl<AssignerDTO>(
                service.getAllAssigner(page, size)
                .getContent()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList())
        );

        return assignerDTOPage;
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
    public Page<AssignerHistoryDTO> getAllAssignerHistory(@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                                       @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size) {

        Page<AssignerHistoryDTO> assignerHistoryDTOPage = new PageImpl<AssignerHistoryDTO>(
                service.getAllAssignerHistory(page, size)
                .getContent()
                .stream()
                .map(this::convertToDtoHistory)
                .collect(Collectors.toList())
        );
        return assignerHistoryDTOPage;
    }

    /**
     * Get an Assigner by id.
     *
     * @param id Integer
     * @return Assigner details
     */
    @GetMapping(path = "/{id}",
            produces = {"application/json"})
    public AssignerDTO getAssignerById(@PathVariable Long id) {
        AssignerDTO assignerDTO = this.convertToDto(service.getAssignerById(id));
        return assignerDTO;
    }

    /**
     * Create an Assigner.
     *
     * @param assigner Assigner
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     */
    @PostMapping(consumes = {"application/json"},
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createAnAssigner(@RequestBody Assigner assigner,
                                 HttpServletRequest request, HttpServletResponse response) {
        Assigner createdAssigner = service.saveAssigner(assigner);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdAssigner.getId()).toString());
    }

    /**
     * Update an existing Assigner.
     *
     * @param assigner Assigner
     * @param id         Long
     */
    @PutMapping(path = "/{id}",
            consumes = {"application/json"},
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAnAssigner(@RequestBody Assigner assigner, @PathVariable Long id) {
        service.updateAssigner(assigner, id);
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

    /**
     * Convert to DTO
     * @param assigner
     * @return assigner DTO
     */
    private AssignerDTO convertToDto(Assigner assigner) {
        AssignerDTO assignerDTO = modelMapper.map(assigner, AssignerDTO.class);
        assignerDTO.setId(assigner.getIdentityNo());
        assignerDTO.getDepartment().setId(assigner.getDepartment().getIdentityNo());
        assignerDTO.getEmployee().setId(assigner.getEmployee().getIdentityNo());
        return assignerDTO;
    }

    /**
     * Convert to History DTO
     * @param assignerHistory
     * @return assignerHistory DTO
     */
    private AssignerHistoryDTO convertToDtoHistory(AssignerHistory assignerHistory) {
        AssignerHistoryDTO assignerHistoryDTO = modelMapper.map(assignerHistory, AssignerHistoryDTO.class);
        assignerHistoryDTO.getDepartment().setId(assignerHistory.getDepartment().getIdentityNo());
        assignerHistoryDTO.getEmployee().setId(assignerHistory.getEmployee().getIdentityNo());
        return assignerHistoryDTO;
    }

}
