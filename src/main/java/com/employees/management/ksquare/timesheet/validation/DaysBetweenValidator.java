package com.employees.management.ksquare.timesheet.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class DaysBetweenValidator implements ConstraintValidator<DaysBetween, Object> {
    private String startDate;
    private String endDate;
    private int dayBetween;

    @Override
    public void initialize(DaysBetween constraintAnnotation) {
        this.startDate = constraintAnnotation.startLocalDate();
        this.endDate = constraintAnnotation.endLocalDate();
        this.dayBetween = constraintAnnotation.daysBetween();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object localDateStart = new BeanWrapperImpl(value)
                .getPropertyValue(startDate);
        Object localEndDate = new BeanWrapperImpl(value)
                .getPropertyValue(endDate);

        if (localDateStart instanceof LocalDate && localEndDate instanceof LocalDate) {
            return DAYS.between((LocalDate) localDateStart, (LocalDate) localEndDate) == dayBetween;
        }
        return false;
    }
}
