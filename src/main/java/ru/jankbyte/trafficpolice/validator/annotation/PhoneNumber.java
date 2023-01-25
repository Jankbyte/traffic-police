package ru.jankbyte.trafficpolice.validator.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Documented;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import ru.jankbyte.trafficpolice.validator.PhoneNumberValidator;

/**
 * Проверяет на корректность поле ({@link java.lang.String}) с номером телефона.
 * <p>Номер телефона должен содержать код страны ({@link ru.jankbyte.trafficpolice.validator.annotation.CountryCode})
 * и последующие 10 цифр.</p>
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumber {
    /**
     * Сообщение в случае ошибки валидации.
     */
    String message() default "Phone number is not valid";

    /**
     * Код страны.
     */
    CountryCode countryCode() default CountryCode.RUSSIA;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
