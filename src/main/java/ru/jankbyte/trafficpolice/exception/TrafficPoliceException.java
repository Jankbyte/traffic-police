package ru.jankbyte.trafficpolice.exception;

/**
 * Базовое исключение, от которого должны наследоваться
 * все остальные создаваемые исключения в приложении.
 */
public abstract class TrafficPoliceException
        extends RuntimeException {
    public TrafficPoliceException(String message) {
        super(message);
    }
}
