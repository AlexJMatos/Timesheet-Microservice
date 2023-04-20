package com.employees.management.ksquare.timesheet.entity;

import com.employees.management.ksquare.timesheet.dto.TimesheetProjectUpdateRequestDTO;
import com.employees.management.ksquare.timesheet.dto.WeekHoursRequestDTO;
import com.employees.management.ksquare.timesheet.entity.enumerators.TimesheetProjectStatus;
import com.employees.management.ksquare.timesheet.exception.EntityNotFoundException;
import com.employees.management.ksquare.timesheet.exception.TimesheetRequestException;
import com.employees.management.ksquare.timesheet.utils.TimesheetUtils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "timesheets")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "week_hours")
    private double weekHours;

    @ManyToOne
    @JoinColumn(name = "requester_id", nullable = false)
    private Employee requester;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "timesheet_id")
    @ToString.Exclude
    private Set<TimesheetProject> projects;

    public void updateTimesheetProjectStatus(UUID timesheetProjectId, TimesheetProjectStatus newStatus) {
        Optional<TimesheetProject> optionalTimesheetProject = this.projects.stream().filter(timesheetProject -> timesheetProject.getId().equals(timesheetProjectId)).findAny();
        optionalTimesheetProject.ifPresentOrElse(timesheetProject -> timesheetProject.setStatus(newStatus),
                () -> {
                    throw new EntityNotFoundException("No timesheet project found for ID - " + timesheetProjectId);
                });
    }

    public void updateTimesheetProjectDraft(TimesheetProjectUpdateRequestDTO updateRequest) {
        Optional<TimesheetProject> optionalTimesheetProject = this.projects.stream().filter(timesheetProject -> timesheetProject.getId().equals(updateRequest.getTimesheetProjectId())).findAny();
        optionalTimesheetProject.ifPresentOrElse(timesheetProject -> {
            if (timesheetProject.getStatus().equals(TimesheetProjectStatus.Draft)) {
                // Update the timesheet project
                timesheetProject.setStatus(updateRequest.isDraft() ? TimesheetProjectStatus.Draft : TimesheetProjectStatus.Pending);
                timesheetProject.setComment(updateRequest.getComment());
                timesheetProject.setHours(TimesheetUtils.convertWeekHoursRequestDTOToProjectHours(updateRequest.getWeekHours()));
                timesheetProject.setExtraHours(TimesheetUtils.convertWeekHoursRequestDTOToProjectExtraHours(updateRequest.getWeekExtraHours()));
                timesheetProject.setAttachmentUrl(updateRequest.getAttachmentUrl());
            } else
                throw new TimesheetRequestException("Timesheet Project with ID - " + timesheetProject.getId() + " is not in draft mode.");
        }, () -> {
            throw new EntityNotFoundException("No timesheet project found for ID - " + updateRequest.getTimesheetProjectId());
        });
    }

    public void deleteTimesheetProject(UUID timesheetProjectId) {
        Optional<TimesheetProject> optionalFound = this.projects.stream().filter(timesheetProject -> timesheetProject.getId().equals(timesheetProjectId)).findAny();
        optionalFound.ifPresentOrElse(timesheetProject -> {
            if (timesheetProject.getStatus().equals(TimesheetProjectStatus.Draft))
                this.projects.remove(timesheetProject);
            else
                throw new TimesheetRequestException("Timesheet Project with ID - " + timesheetProject.getId() + " is not in draft mode.");
        }, () -> {
            throw new EntityNotFoundException("Timesheet project not found for ID - " + timesheetProjectId);
        });
    }
}
