package com.employees.management.ksquare.timesheet.controller;

import com.employees.management.ksquare.timesheet.dto.*;
import com.employees.management.ksquare.timesheet.service.TimesheetService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("timesheets")
@Log4j2
public class TimesheetController {
    @Autowired
    private TimesheetService timesheetService;

    @GetMapping
    public ResponseEntity<Page<TimesheetDTO>> getTimesheetCollection(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String[] sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<TimesheetDTO> allTimesheet = timesheetService.getAllTimesheet(pageable);
        log.info("Timesheet found: {}", allTimesheet);
        return ResponseEntity.ok(allTimesheet);
    }

    @PostMapping
    public ResponseEntity<TimesheetDTO> addTimesheet(@Valid @RequestBody TimesheetRequestDTO dto) {
        log.info("Timesheet Request DTO: {}", dto);
        TimesheetDTO newTimesheet = timesheetService.addTimesheet(dto);
        return new ResponseEntity<>(newTimesheet, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TimesheetDTO> getTimesheet(@PathVariable("id") UUID uuid) {
        log.info("UUID: {}", uuid);
        TimesheetDTO timesheet = timesheetService.getTimesheetByID(uuid);
        log.info("Timesheet: {}", timesheet);
        EmployeeDTO employee = timesheet.getRequester();
        log.info("Requester: {}", employee);
        return ResponseEntity.ok(timesheet);
    }

    @GetMapping(value = "/{id}/projects")
    public ResponseEntity<Set<TimesheetProjectDTO>> getTimesheetProjects(@PathVariable("id") UUID uuid) {
        log.info("UUID: {}", uuid);
        TimesheetDTO timesheet = timesheetService.getTimesheetByID(uuid);
        log.info("Timesheet: {}", timesheet);
        EmployeeDTO employee = timesheet.getRequester();
        log.info("Requester: {}", employee);
        return ResponseEntity.ok(timesheet.getProjects());
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<TimesheetDTO> patchTimesheet(@PathVariable("id") UUID uuid,
                                                       @RequestBody @NotEmpty(message = "Timesheet project body array cannot be empty")
                                                       List<@Valid TimesheetProjectStatusPatchRequestDTO> timesheetProjectPatchList) {
        log.info("UUID: {}", uuid);
        TimesheetDTO patchTimesheet = timesheetService.updateTimesheetProjectStatusById(uuid, timesheetProjectPatchList);
        log.info("Patch Timesheet: {}", patchTimesheet);
        return ResponseEntity.ok(patchTimesheet);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TimesheetDTO> updateTimesheetProjects(@PathVariable("id") UUID uuid,
                                                                @RequestBody @NotEmpty(message = "Timesheet project body array cannot be empty")
                                                                List<@Valid TimesheetProjectUpdateRequestDTO> projectRequestList) {
        log.info("UUID: {}", uuid);
        TimesheetDTO updatedTimesheet = timesheetService.updateTimesheetProjects(uuid, projectRequestList);
        return ResponseEntity.ok(updatedTimesheet);
    }
}