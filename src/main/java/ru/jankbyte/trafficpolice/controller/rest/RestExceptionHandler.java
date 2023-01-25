package ru.jankbyte.trafficpolice.controller.rest;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;

import ru.jankbyte.trafficpolice.model.ValidationMessage;
import ru.jankbyte.trafficpolice.model.ExceptionMessage;
import ru.jankbyte.trafficpolice.exception.*;

import java.sql.SQLException;
import jakarta.validation.ConstraintViolationException;

import org.springframework.http.converter.HttpMessageNotReadableException;

@RestControllerAdvice(basePackages =
    "ru.jankbyte.trafficpolice.controller.rest")
public class RestExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ExceptionMessage handleBadRequests(Exception exp) {
        return new ExceptionMessage(exp);
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(AccessDeniedException.class)
    public ExceptionMessage handleNotAllowedException(Exception exp) {
        var message = new ExceptionMessage(exp);
        message.setMessage("Недостаточно прав");
        return message;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {EmptyResultDataAccessException.class,
        EntityNotFoundException.class})
    public ExceptionMessage handleExceptionNotFound(Exception exp) {
        return new ExceptionMessage(exp);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({AddingViolationException.class,
        CarChangeOwnerException.class, SQLException.class})
    public ExceptionMessage handleInternalExceptions(Exception exp) {
        return new ExceptionMessage(exp);
    }
    

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {
        MethodArgumentNotValidException.class,
        ConstraintViolationException.class})
    public ValidationMessage handleValidationExceptions(Exception exception) {
        var msg = new ValidationMessage(exception);
        msg.setMessage("Ошибка при заполнении полей");
        return msg;
    }
}
