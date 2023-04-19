package com.employees.management.ksquare.timesheet.service.impl;

import com.employees.management.ksquare.timesheet.dto.*;
import com.employees.management.ksquare.timesheet.entity.Timesheet;
import com.employees.management.ksquare.timesheet.entity.enumerators.TimesheetProjectStatus;
import com.employees.management.ksquare.timesheet.exception.EntityNotFoundException;
import com.employees.management.ksquare.timesheet.repository.TimesheetProjectRepository;
import com.employees.management.ksquare.timesheet.repository.TimesheetRepository;
import com.employees.management.ksquare.timesheet.service.TimesheetService;
import com.employees.management.ksquare.timesheet.utils.TimesheetUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TimesheetServiceImpl implements TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private TimesheetProjectRepository timesheetProjectRepository;

    @Autowired
    private TimesheetUtils utils;

    @Override
    public TimesheetDTO getTimesheetByID(UUID uuid) {
        return utils.convertTimesheetEntityToDTO(retrieveTimesheet(uuid));
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
    public Page<TimesheetDTO> getAllTimesheet(Pageable pageable) {
        Page<Timesheet> timesheetPage = timesheetRepository.findAll(pageable);
        if (timesheetPage.isEmpty()) throw new EntityNotFoundException("No timesheet found");
        return timesheetPage.map(timesheet -> utils.convertTimesheetEntityToDTO(timesheet));
    }

    @Override
    public TimesheetDTO updateTimesheetProjectStatusById(UUID uuid, List<TimesheetProjectStatusPatchRequestDTO> timesheetProjectStatusPatchRequestList) {
        // Retrieve the timesheet
        Timesheet timesheet = retrieveTimesheet(uuid);

        // Set the new status for the timesheet project or throw an exception in case the id is not found
        timesheetProjectStatusPatchRequestList.forEach(request -> timesheet.updateTimesheetProjectStatus(request.getTimesheetProjectId(), TimesheetProjectStatus.valueOf(request.getStatus())));

        // Save the timesheet
        Timesheet update = timesheetRepository.save(timesheet);

        return utils.convertTimesheetEntityToDTO(update);
    }

    @Override
    public TimesheetDTO updateTimesheetProjects(UUID uuid, List<TimesheetProjectUpdateRequestDTO> timesheetProjectRequestList) {
        // Retrieve the timesheet project from timesheet
        Timesheet timesheet = retrieveTimesheet(uuid);

        // Update the timesheet project if it's in draft status, else throw an exception
        timesheetProjectRequestList.forEach(timesheet::updateTimesheetProjectDraft);

        // Save the timesheet
        Timesheet update = timesheetRepository.save(timesheet);

        return utils.convertTimesheetEntityToDTO(update);
    }

    private Timesheet retrieveTimesheet(UUID uuid){
        return timesheetRepository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Timesheet not found - UUID: %s", uuid)));
    }
}
