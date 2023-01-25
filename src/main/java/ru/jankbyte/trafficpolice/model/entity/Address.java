package ru.jankbyte.trafficpolice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

import lombok.*;

@Entity
@Table(name = "addresses",
    schema = "persons_info")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Address extends BaseEntity {
    @NotBlank(message = "Город не указан")
    @Column(nullable = false)
    private String city;

    @NotBlank(message = "Улица не указана")
    @Column(nullable = false)
    private String street;

    @NotBlank(message = "Дом не указан")
    @Column(nullable = false)
    private String house;

    @NotBlank(message = "Квартира не указана")
    @Column(nullable = false)
    private String flat;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Person person;

    @Override
    public int hashCode() {
        return Objects.hash(city, street, house, flat);
    }

    @Override
    public String toString() {
        return """
            Address(city=%s, \
            street=%s, \
            house=%s, \
            flat=%s\
            )\
            """.formatted(city, street, house, flat);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (object instanceof Address address) {
            return Objects.equals(city, address.city) &&
                    Objects.equals(street, address.street) &&
                    Objects.equals(house, address.house) &&
                    Objects.equals(flat, address.flat);
        }
        return false;
    }
}
