package com.employees.management.ksquare.timesheet.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class WeekHoursRequestDTO {
    @Min(value = 0, message = "The number of hours cannot be less than 0")
    @Max(value = 8, message = "The number of hours cannot be greater than 8")
    private double monday;

    @Min(value = 0, message = "The number of hours cannot be less than 0")
    @Max(value = 8, message = "The number of hours cannot be greater than 8")
    private double tuesday;

    @Min(value = 0, message = "The number of hours cannot be less than 0")
    @Max(value = 8, message = "The number of hours cannot be greater than 8")
    private double wednesday;

    @Min(value = 0, message = "The number of hours cannot be less than 0")
    @Max(value = 8, message = "The number of hours cannot be greater than 8")
    private double thursday;

    @Min(value = 0, message = "The number of hours cannot be less than 0")
    @Max(value = 8, message = "The number of hours cannot be greater than 8")
    private double friday;

    @Min(value = 0, message = "The number of hours cannot be less than 0")
    @Max(value = 8, message = "The number of hours cannot be greater than 8")
    private double saturday;

    @Min(value = 0, message = "The number of hours cannot be less than 0")
    @Max(value = 8, message = "The number of hours cannot be greater than 8")
    private double sunday;
}
