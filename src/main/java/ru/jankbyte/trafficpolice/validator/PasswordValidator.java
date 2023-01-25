package ru.jankbyte.trafficpolice.validator;

import ru.jankbyte.trafficpolice.validator.annotation.Password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator
        implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String value,
            ConstraintValidatorContext context) {
        var pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])" +
            "(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return (value == null) || (value != null && 
            value.matches(pattern));
    }
}
