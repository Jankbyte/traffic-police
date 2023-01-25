package ru.jankbyte.trafficpolice.validator.annotation;

/**
 * Содержит коды стран для валидации телефонного номера ({@link PhoneNumber}).
 */
public enum CountryCode {
    /**
     * Российский номер (+7)
     */
    RUSSIA(7);

    private final Integer code;

    CountryCode(Integer code) {
        this.code = code;
    }

    /**
     * Возвращает код страны.
     */
    public Integer getCode() {
        return code;
    }
}
