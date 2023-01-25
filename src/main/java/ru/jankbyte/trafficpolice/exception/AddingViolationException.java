package ru.jankbyte.trafficpolice.exception;

public class AddingViolationException
        extends TrafficPoliceException {
    public AddingViolationException() {
        super("""
            Невозможно добавить нарушение, \
            т.к. у машины отсутствует владелец""");
    }
}
