package ru.jankbyte.trafficpolice.validator.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Documented;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import ru.jankbyte.trafficpolice.validator.PasswordValidator;

/**
 * Проверяет на корректный уровень безопасности пароль пользователя.
 * <p>Критерии для прохождения валидации:</p>
 * <ul>
 *   <li>Содержит минимум одну цифру</li>
 *   <li>Содержит минимум одну строчную букву</li>
 *   <li>Содержит минимум одну заглавную букву</li>
 *   <li>Содержит минимум один специальный символ (#!.$_)</li>
 *   <li>Не содержит пробелов</li>
 *   <li>Не короче 8 символов</li>
 * </ul>
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    /**
     * Сообщение, если пароль провалил валидацию.
     */
    String message() default "Password is easy";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
