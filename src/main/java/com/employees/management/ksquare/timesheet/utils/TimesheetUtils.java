package com.employees.management.ksquare.timesheet.utils;

import com.employees.management.ksquare.timesheet.dto.*;
import com.employees.management.ksquare.timesheet.entity.*;
import com.employees.management.ksquare.timesheet.entity.enumerators.TimesheetProjectStatus;
import com.employees.management.ksquare.timesheet.service.EmployeeService;
import com.employees.management.ksquare.timesheet.service.ProjectService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Component
@Log4j2
public class TimesheetUtils {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    public TimesheetDTO convertTimesheetEntityToDTO(Timesheet entity) {
        return TimesheetDTO.builder()
                .id(entity.getId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .weekHours(entity.getWeekHours())
                .requester(convertEmployeeEntityToDTO(entity.getRequester()))
                .projects(entity.getProjects().stream().map(this::convertTimesheetProjectEntityToDTO).collect(Collectors.toSet()))
                .build();
    }

    public EmployeeDTO convertEmployeeEntityToDTO(Employee entity) {
        return EmployeeDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .jobEmail(entity.getJobEmail())
                .position(entity.getPosition())
                .country(entity.getCountry())
                .photoUrl(entity.getPhotoUrl())
                .build();
    }

    public TimesheetProjectDTO convertTimesheetProjectEntityToDTO(TimesheetProject entity) {
        return TimesheetProjectDTO.builder()
                .id(entity.getId())
                .project(convertProjectEntityToDTO(entity.getProject()))
                .status(entity.getStatus())
                .projectHours(entity.getProjectHours())
                .comment(entity.getComment())
                .approves(convertEmployeeEntityToDTO(entity.getApproves()))
                .hours(convertProjectHoursEntityToDTO(entity.getHours()))
                .build();
    }

    public ProjectDTO convertProjectEntityToDTO(Project entity) {
        return ProjectDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .isActive(entity.isActive())
                .projectOwner(entity.getProjectOwner())
                .pmo(convertEmployeeEntityToDTO(entity.getPmo()))
                .costUSD(entity.getCostUSD())
                .build();
    }

    public ProjectHoursDTO convertProjectHoursEntityToDTO(ProjectHours entity) {
        return ProjectHoursDTO.builder()
                .monday(entity.getMondayHours())
                .tuesday(entity.getTuesdayHours())
                .wednesday(entity.getWednesdayHours())
                .thursday(entity.getThursdayHours())
                .friday(entity.getFridayHours())
                .saturday(entity.getSaturdayHours())
                .sunday(entity.getSundayHours())
                .build();
    }

    public Timesheet convertTimesheetRequestDTOToEntity(TimesheetRequestDTO dto) {
        Employee requester = employeeService.findEmployeeByUUID(dto.getRequesterId());
        Set<TimesheetProject> timesheetProjects = dto.getProjects().stream()
                .map(this::convertTimesheetProjectRequestDTOToTimesheetProject).collect(Collectors.toSet());
        log.info("Timesheet Projects: {}", timesheetProjects);

        return Timesheet.builder()
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .requester(requester)
                .weekHours(timesheetProjects.stream().map(TimesheetProject::getProjectHours).reduce(0d, Double::sum))
                .projects(timesheetProjects)
                .build();
    }

    private TimesheetProject convertTimesheetProjectRequestDTOToTimesheetProject(TimesheetProjectRequestDTO dto){
        Project project = projectService.findProjectByUUID(dto.getProjectId());
        ProjectHours hours = convertWeekHoursRequestDTOToProjectHours(Optional.ofNullable(dto.getWeekHours()).orElse(WeekHoursRequestDTO.builder().build()));
        ProjectExtraHours extraHours = convertWeekHoursRequestDTOToProjectExtraHours(Optional.ofNullable(dto.getWeekExtraHours()).orElse(WeekHoursRequestDTO.builder().build()));
        return TimesheetProject.builder()
                .project(project)
                .status(dto.isDraft() ? TimesheetProjectStatus.Draft : TimesheetProjectStatus.Pending)
                .projectHours(hours.getTotalHours() + extraHours.getTotalHours())
                .comment(dto.getComment() == null ? "" : dto.getComment())
                .hours(hours.getTotalHours() != 0 ? hours : null)
                .extraHours(extraHours.getTotalHours() != 0 ? extraHours : null)
                .approves(project.getPmo())
                .build();
    }

    public static ProjectHours convertWeekHoursRequestDTOToProjectHours(WeekHoursRequestDTO dto) {
        return ProjectHours.builder()
                .mondayHours(dto.getMonday())
                .tuesdayHours(dto.getTuesday())
                .wednesdayHours(dto.getWednesday())
                .thursdayHours(dto.getThursday())
                .fridayHours(dto.getFriday())
                .saturdayHours(dto.getSaturday())
                .sundayHours(dto.getSunday())
                .build();
    }

    public static ProjectExtraHours convertWeekHoursRequestDTOToProjectExtraHours(WeekHoursRequestDTO dto) {
        return ProjectExtraHours.builder()
                .mondayHours(dto.getMonday())
                .tuesdayHours(dto.getTuesday())
                .wednesdayHours(dto.getWednesday())
                .thursdayHours(dto.getThursday())
                .fridayHours(dto.getFriday())
                .saturdayHours(dto.getSaturday())
                .sundayHours(dto.getSunday())
                .build();
    }
}
