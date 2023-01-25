package ru.jankbyte.trafficpolice.exception;

public class CarChangeOwnerException
        extends TrafficPoliceException {
    public CarChangeOwnerException() {
        super("""
            Невозможно сменить владельца, \
            т.к. есть активные нарушения""");
    }
}
