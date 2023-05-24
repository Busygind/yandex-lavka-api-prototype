package ru.yandex.yandexlavka.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ListOfTimeIntervalsValidator.class)
public @interface TimeIntervals {

    String message() default "List cannot contain strings that isn't a time interval";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
