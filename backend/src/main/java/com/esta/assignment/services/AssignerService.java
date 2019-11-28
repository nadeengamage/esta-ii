package com.esta.assignment.services;

import com.esta.assignment.exceptions.ResourceNotFoundException;
import com.esta.assignment.models.Assigner;
import com.esta.assignment.models.Employee;
import com.esta.assignment.models.audits.AssignerHistory;
import com.esta.assignment.repositories.AssignerRepository;
import com.esta.assignment.repositories.audit.AssignerHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Assigner service.
 */
@Service
public class AssignerService {

    @Autowired
    private AssignerRepository repository;

    @Autowired
    private AssignerHistoryRepository historyRepository;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Fetch all assigners from the repository.
     * @param page Integer
     * @param size Integer
     * @return List<Assigner> All assigners.
     */
    public Page<Assigner> getAllAssigner(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Assigner> pageOfAssigners = repository.findAll(pageable);
        return pageOfAssigners;
    }

    /**
     * Get an existing Assigner.
     * @param assignerId Long
     * @return Assigner details.
     */
    public Assigner getAssignerById(Long assignerId) {
        return repository.findById(assignerId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Assigner %s is couldn't found!", assignerId)));
    }

    /**
     * Create an Assigner.
     * @param assigner Assigner
     * @return Assigner details.
     */
    public Assigner saveAssigner(Assigner assigner) {
        checkEmployeeAssignment(assigner);

        // Save the assignment
        Assigner createdAssigner = repository.saveAndFlush(assigner);
        AssignerHistory history = new AssignerHistory(createdAssigner, "Inserted an Assigner", "CREATED");
        java.util.Date date = new java.util.Date();
        history.setHistoryDate(new Timestamp(date.getTime()));
        historyRepository.save(history);
        return createdAssigner;
    }

    /**
     * Update an existing Assigner.
     * @param assigner Assigner
     * @param id Long
     */
    public void updateAssigner(Assigner assigner, Long id) {
        checkEmployeeAssignment(assigner);

        Assigner exitsAssigner = getAssignerById(id);
        assigner.setId(exitsAssigner.getId());
        repository.save(assigner);
    }

    /**
     * Delete an existing Assigner
     * @param id Long
     */
    public void deleteAssigner(Long id) {
        Assigner exitsAssigner = getAssignerById(id);
        repository.save(exitsAssigner);
    }

    /**
     * Validation of the assigned time.
     * @param assigner
     */
    private void checkEmployeeAssignment(Assigner assigner) {
        Employee employee = employeeService.getEmployeeById(assigner.getEmployee().getId());
        List<Time> times = repository.findByEmployeeId(employee.getId());
        times.add(assigner.getWorkingHours());

        try {
            String totalTime = sumOfTimes(times);

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            Date totalWorkingHours = timeFormat.parse(totalTime);
            Date assignedWorkingHours = timeFormat.parse(employee.getWorkingHours().toString());

            int output = totalWorkingHours.compareTo(assignedWorkingHours);

            if (output > 0) {
                throw new RuntimeException("Cannot assign the employee, because assigned time exceeded!");
            }

        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sum of the times.
     * @param times
     * @return String time.
     */
    private String sumOfTimes(List<Time> times) {
        int hours = 0;
        int minutes = 0;
        int seconds = 0;

        for(int i = 0; i < times.size(); i++) {
            hours += Integer.parseInt(times.get(i).toString().split(":")[0]);
            minutes += Integer.parseInt(times.get(i).toString().split(":")[1]);
            seconds += Integer.parseInt(times.get(i).toString().split(":")[2]);

            if (seconds >= 60) {
                minutes ++;
                seconds = seconds % 60;
            }

            if (minutes >= 60) {
                hours ++;
                minutes = minutes % 60;
            }
        }

        String totalTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return totalTime;
    }
}
