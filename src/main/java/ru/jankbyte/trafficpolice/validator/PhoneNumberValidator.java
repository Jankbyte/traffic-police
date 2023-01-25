package ru.jankbyte.trafficpolice.validator;

import ru.jankbyte.trafficpolice.validator.annotation.PhoneNumber;
import ru.jankbyte.trafficpolice.validator.annotation.CountryCode;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

public class PhoneNumberValidator
        implements ConstraintValidator<PhoneNumber, String> {
    private CountryCode countryCode;

    @Override
    public void initialize(PhoneNumber phoneNumber) {
        this.countryCode = phoneNumber.countryCode();
    }

    @Override
    public boolean isValid(String value,
            ConstraintValidatorContext context) {
        var pattern = "^(%d){1}(\\d){10}$"
            .formatted(countryCode.getCode());
        return value != null && value.matches(pattern);
    }
}
