package ru.jankbyte.trafficpolice.validator.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Documented;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import ru.jankbyte.trafficpolice.validator.CarNumberValidator;

/**
 * Аннотация проверяет поле ({@link java.lang.String}) на корректность
 * номера автомобиля.
 * <p>Примеры</p>
 * <ul>
 *   <li>Валидный: А234ДЗ</li>
 *   <li>Не валидный: а234дз</li>
 * </ul>
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CarNumberValidator.class)
public @interface CarNumber {
    String message() default "Car number is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
