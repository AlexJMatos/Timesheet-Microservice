package com.employees.management.ksquare.timesheet.service;

import com.employees.management.ksquare.timesheet.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Time;
import java.util.List;
import java.util.UUID;

public interface TimesheetService {
    TimesheetDTO getTimesheetByID(UUID uuid);
    List<TimesheetDTO> getTimesheetByEmployeeID(UUID uuid);
    TimesheetDTO addTimesheet(TimesheetRequestDTO dto);
    Page<TimesheetDTO> getAllTimesheet(Pageable pageable);
    TimesheetDTO updateTimesheetProjectStatusById(UUID uuid, List<TimesheetProjectStatusPatchRequestDTO> timesheetProjectPatchBody);
    TimesheetDTO updateTimesheetProjects(UUID uuid, List<TimesheetProjectUpdateRequestDTO> updateTimesheetProjectRequestList);
    void deleteTimesheetProject(UUID timesheetId, UUID timesheetProjectId);
}
