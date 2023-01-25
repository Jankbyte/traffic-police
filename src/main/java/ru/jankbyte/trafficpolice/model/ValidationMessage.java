package ru.jankbyte.trafficpolice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.ObjectError;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ConstraintViolation;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Представление JSON-ответа в случае ошибки при
 * валидации параметров, когда создаётся/редактируется
 * участник.
 */
public final class ValidationMessage
        extends ExceptionMessage {
    @JsonProperty("details")
    private List<String> details = new ArrayList<>();

    public ValidationMessage(Exception exception) {
        super(exception);
        putErrors(exception);
    }

    /**
     * Добавляет ошибку в список.
     * <p>Нужно на тот случай, если нужно добавить 
     * "кастомную" ошибку в список (дабы не вызывать getDetails()).</p>
     */
    public void addDetail(String detail) {
        details.add(detail);
    }

    /**
     * Возвращает список ошибок.
     * @return Список ошибок
     */
    public List<String> getDetails() {
        return Collections.unmodifiableList(details);
    }

    private void putErrors(Exception exception) {
        if (exception instanceof MethodArgumentNotValidException exp) {
            addErrorsFromException(exp);
        } else if (exception instanceof ConstraintViolationException exp) {
            addErrorsFromException(exp);
        } else if (exception instanceof TransactionSystemException exp) {
            var rootException = (Exception) exp.getRootCause();
            putErrors(rootException);
        }
    }

    private void addErrorsFromException(
            MethodArgumentNotValidException exp) {
        exp.getAllErrors().stream()
            .map(ObjectError::getDefaultMessage)
                .forEach(details::add);
    }

    private void addErrorsFromException(
            ConstraintViolationException exp) {
        exp.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage)
                .forEach(details::add);
    }
}
