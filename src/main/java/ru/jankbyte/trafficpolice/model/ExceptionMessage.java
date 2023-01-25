package ru.jankbyte.trafficpolice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * Базовые объект для создания REST-ответов на
 * исключения.
 */
@JsonTypeName("exception")
@JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.NAME)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionMessage {
    private String message;

    @JsonProperty("class_name")
    private String className;

    public ExceptionMessage(Exception exp) {
        setMessage(exp.getMessage());
        setClassName(exp.getClass().getName());
    }

    public final void setMessage(String message) {
        this.message = message;
    }

    public final String getMessage() {
        return message;
    }

    public final void setClassName(String className) {
        this.className = className;
    }

    public final String getClassName() {
        return className;
    }
}
