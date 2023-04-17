package com.employees.management.ksquare.timesheet.controller;

import com.employees.management.ksquare.timesheet.dto.EmployeeDTO;
import com.employees.management.ksquare.timesheet.dto.TimesheetDTO;
import com.employees.management.ksquare.timesheet.dto.TimesheetRequestDTO;
import com.employees.management.ksquare.timesheet.repository.TimesheetRepository;
import com.employees.management.ksquare.timesheet.service.TimesheetService;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("timesheets")
@Log4j2
public class TimesheetController {
    @Autowired
    private TimesheetService timesheetService;

    @Autowired
    private TimesheetRepository repository;

    @GetMapping
    public ResponseEntity<List<TimesheetDTO>> getTimesheetCollection() {
        return ResponseEntity.ok(timesheetService.getAllTimesheet());
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

    @PostMapping
    public ResponseEntity<TimesheetDTO> addTimesheet(@Valid @RequestBody TimesheetRequestDTO dto) {
        log.info("Timesheet Request DTO: {}", dto);
        TimesheetDTO newTimesheet = timesheetService.addTimesheet(dto);
        return new ResponseEntity<>(newTimesheet, HttpStatus.CREATED);
    }
}