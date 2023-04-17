package validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DaysBetweenValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DaysBetween {
    String message() default "The days between don't match";
    String startLocalDate();

    String endLocalDate();

    int daysBetween() default 0;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
