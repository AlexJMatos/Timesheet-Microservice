package com.employees.management.ksquare.timesheet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimesheetProjectRequestDTO {
    @NotNull(message = "The project id cannot empty")
    private UUID projectId;
    @NotNull(message = "Enter true or false if the timesheet project is draft")
    private boolean isDraft;
    private String comment;
    private String attachmentUrl;
    private WeekHoursRequestDTO weekHours;
    private WeekHoursRequestDTO weekExtraHours;
}
