package com.employees.management.ksquare.timesheet.entity.enumerators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TimesheetProjectStatus {
    Pending("Pending"),
    Rejected("Rejected"),
    Approved("Approved"),
    Draft("Draft");

    @Getter
    private final String value;
}
