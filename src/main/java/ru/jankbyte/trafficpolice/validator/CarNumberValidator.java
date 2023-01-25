package ru.jankbyte.trafficpolice.validator;

import ru.jankbyte.trafficpolice.validator.annotation.CarNumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

public class CarNumberValidator
        implements ConstraintValidator<CarNumber, String> {
    @Override
    public boolean isValid(String value,
            ConstraintValidatorContext context) {
        var pattern = "^([А-Я]){1}([0-9]){3}([А-Я]){2}$";
        return value != null && value.matches(pattern);
    }
}
