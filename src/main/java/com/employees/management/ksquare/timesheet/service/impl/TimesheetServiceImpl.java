package com.employees.management.ksquare.timesheet.service.impl;

import com.employees.management.ksquare.timesheet.dto.TimesheetDTO;
import com.employees.management.ksquare.timesheet.dto.TimesheetProjectRequestDTO;
import com.employees.management.ksquare.timesheet.dto.TimesheetRequestDTO;
import com.employees.management.ksquare.timesheet.entity.Timesheet;
import com.employees.management.ksquare.timesheet.exception.EntityNotFoundException;
import com.employees.management.ksquare.timesheet.exception.TimesheetRequestException;
import com.employees.management.ksquare.timesheet.repository.TimesheetRepository;
import com.employees.management.ksquare.timesheet.service.EmployeeService;
import com.employees.management.ksquare.timesheet.service.ProjectService;
import com.employees.management.ksquare.timesheet.service.TimesheetService;
import com.employees.management.ksquare.timesheet.utils.TimesheetUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Log4j2
public class TimesheetServiceImpl implements TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private TimesheetUtils utils;

    @Override
    public TimesheetDTO getTimesheetByID(UUID uuid) {
        return utils.convertTimesheetEntityToDTO(timesheetRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Timesheet not found - UUID: %s", uuid))));
    }

    @Override
    public List<TimesheetDTO> getTimesheetByEmployeeID(UUID uuid) {
        return timesheetRepository.findAll().stream().filter(Objects::nonNull)
                .map(timesheet -> utils.convertTimesheetEntityToDTO(timesheet))
                .collect(Collectors.toList());
    }

    @Override
    public TimesheetDTO addTimesheet(TimesheetRequestDTO dto) {
        // Create timesheet entity based on dto
        Timesheet newTimesheet = utils.convertTimesheetRequestDTOToEntity(dto);
        Timesheet saved = timesheetRepository.save(newTimesheet);
        return utils.convertTimesheetEntityToDTO(saved);
    }

    @Override
    public List<TimesheetDTO> getAllTimesheet() {
        List<Timesheet> timesheetList = timesheetRepository.findAll();
        if (timesheetList.isEmpty()) throw new EntityNotFoundException("No timesheets found");
        return timesheetList.stream()
                .filter(Objects::nonNull)
                .map(timesheet -> utils.convertTimesheetEntityToDTO(timesheet))
                .collect(Collectors.toList());
    }
}
