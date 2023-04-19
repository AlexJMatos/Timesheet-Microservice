package com.employees.management.ksquare.timesheet.validation;

import com.employees.management.ksquare.timesheet.entity.enumerators.TimesheetProjectStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class TimesheetStatusValidator implements ConstraintValidator<TimesheetProjectStatusType, String> {
    private TimesheetProjectStatus[] subset;

    @Override
    public void initialize(TimesheetProjectStatusType constraintAnnotation) {
        this.subset = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            return Arrays.asList(subset).contains(TimesheetProjectStatus.valueOf(value));
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
