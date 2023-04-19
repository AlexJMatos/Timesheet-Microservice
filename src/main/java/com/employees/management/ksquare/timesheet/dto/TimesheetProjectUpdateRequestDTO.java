package com.employees.management.ksquare.timesheet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimesheetProjectUpdateRequestDTO {
    @NotNull(message = "Timesheet project id cannot empty")
    private UUID timesheetProjectId;
    @NotNull(message = "Enter true or false if the timesheet project is draft")
    private boolean isDraft;
    private String comment;
    private String attachmentUrl;
    private WeekHoursRequestDTO weekHours;
    private WeekHoursRequestDTO weekExtraHours;
}
