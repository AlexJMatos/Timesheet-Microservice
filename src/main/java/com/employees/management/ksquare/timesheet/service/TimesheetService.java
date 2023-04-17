package com.employees.management.ksquare.timesheet.service;

import com.employees.management.ksquare.timesheet.dto.TimesheetDTO;
import com.employees.management.ksquare.timesheet.dto.TimesheetRequestDTO;

import java.util.List;
import java.util.UUID;

public interface TimesheetService {
    TimesheetDTO getTimesheetByID(UUID uuid);
    List<TimesheetDTO> getTimesheetByEmployeeID(UUID uuid);
    TimesheetDTO addTimesheet(TimesheetRequestDTO dto);
    List<TimesheetDTO> getAllTimesheet();
}
