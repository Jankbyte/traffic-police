package ru.jankbyte.trafficpolice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import ru.jankbyte.trafficpolice.validator.annotation.PhoneNumber;
import ru.jankbyte.trafficpolice.validator.annotation.CountryCode;

import lombok.*;

@Entity
@Table(name = "contacts",
    schema = "persons_info")
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Contact extends BaseEntity {
    @Email(message =
        "Неверный формат адреса электронной почты")
    @Column(unique = true)
    private String email;

    @PhoneNumber(countryCode = CountryCode.RUSSIA,
        message = "Неверный формат номера телефона")
    @JsonProperty("phone_number")
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @JsonIgnore
    @OneToOne(mappedBy = "contact")
    private Person person;

    @Override
    public int hashCode() {
        return Objects.hash(email, phoneNumber);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (object instanceof Contact contact) {
            return Objects.equals(email, contact.email) &&
                    Objects.equals(phoneNumber, contact.phoneNumber);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Contact(phoneNumber=%s, email=%s)"
            .formatted(phoneNumber, email);
    }
}
