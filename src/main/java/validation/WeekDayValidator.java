package validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekDayValidator implements ConstraintValidator<WeekDay, LocalDate> {
    private DayOfWeek valueToMatch;

    @Override
    public void initialize(WeekDay constraintAnnotation) {
        valueToMatch = constraintAnnotation.day();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return value.getDayOfWeek().equals(valueToMatch);
    }
}
