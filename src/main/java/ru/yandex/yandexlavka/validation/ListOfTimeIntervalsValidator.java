package ru.yandex.yandexlavka.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ListOfTimeIntervalsValidator implements ConstraintValidator<TimeIntervals, List<String>> {

    @Override
    public void initialize(TimeIntervals timeIntervals) {
    }

    @Override
    public boolean isValid(List<String> objects, ConstraintValidatorContext context) {
        return objects.stream().allMatch(nef -> nef.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]-([01]?[0-9]|2[0-3]):[0-5][0-9]$"));
    }

}
