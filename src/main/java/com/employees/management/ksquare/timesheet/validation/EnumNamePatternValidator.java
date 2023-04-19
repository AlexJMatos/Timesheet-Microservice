package com.employees.management.ksquare.timesheet.validation;

import com.employees.management.ksquare.timesheet.entity.enumerators.TimesheetProjectStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class EnumNamePatternValidator implements ConstraintValidator<TimesheetProjectStatusPattern, TimesheetProjectStatus> {
    private Pattern pattern;

    @Override
    public void initialize(TimesheetProjectStatusPattern annotation) {
        try {
            pattern = Pattern.compile(annotation.regexp());
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Given regex is invalid", e);
        }
    }

    @Override
    public boolean isValid(TimesheetProjectStatus value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Matcher m = pattern.matcher(value.getValue());
        return m.matches();
    }
}