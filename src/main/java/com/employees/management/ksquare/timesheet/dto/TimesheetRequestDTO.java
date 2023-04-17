package com.employees.management.ksquare.timesheet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import validation.DaysBetween;
import validation.WeekDay;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DaysBetween(startLocalDate = "startDate", endLocalDate = "endDate", daysBetween = 6, message = "startDate and endDate should be one week apart (6 days)")
public class TimesheetRequestDTO {
    @NotNull(message = "The requester id cannot be empty")
    private UUID requesterId;

    @NotNull(message = "The starter date cannot be empty")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @WeekDay(day = DayOfWeek.MONDAY, message = "startDate must be monday")
    private LocalDate startDate;

    @NotNull(message = "The end date cannot be empty")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @WeekDay(day = DayOfWeek.SUNDAY, message = "endDate must be sunday")
    private LocalDate endDate;

    @NotEmpty(message = "Projects cannot be empty")
    private List<TimesheetProjectRequestDTO> projects;
}