package com.employees.management.ksquare.timesheet.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.DayOfWeek;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {WeekDayValidator.class})
@Retention(RUNTIME)
public @interface WeekDay {
    String message() default "Invalid day of week";

    DayOfWeek day() default DayOfWeek.MONDAY;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
