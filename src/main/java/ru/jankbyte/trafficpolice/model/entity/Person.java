package ru.jankbyte.trafficpolice.model.entity;

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "persons",
    schema = "persons_info")
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"id", "firstName", "lastName",
    "patronymic", "birthDate", "contact", "address"})
public class Person extends BaseEntity {
    @NotBlank(message = "Не указано имя")
    @Column(name = "first_name",
        nullable = false)
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank(message = "Не указана фамилия")
    @Column(name = "last_name",
        nullable = false)
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank(message = "Не указано отчество")
    @Column(nullable = false)
    private String patronymic;

    @NotNull(message = "Не указана дата рождения")
    @Basic
    @Column(name = "birth_date", nullable = false)
    @JsonProperty("birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd", timezone = "GMT+3")
    private Date birthDate;

    @Valid
    @NotNull(message = "Адрес не найден")
    @OneToOne(cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JoinColumn(name = "address_id",
        referencedColumnName = "id")
    private Address address;

    @Valid
    @NotNull(message = "Контакты не найдены")
    @OneToOne(cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JoinColumn(name = "contact_id",
        referencedColumnName = "id")
    private Contact contact;

    @OneToMany(cascade = {
        CascadeType.DETACH, CascadeType.MERGE, 
        CascadeType.PERSIST, CascadeType.REFRESH},
        fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id",
        referencedColumnName = "id")
    @JsonIgnore
    @Builder.Default
    private Set<Car> cars = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JoinColumn(name = "violator_id",
        referencedColumnName = "id")
    @JsonIgnore
    @Builder.Default
    private Set<Violation> violations = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName,
            birthDate, address);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (object instanceof Person person) {
            return Objects.equals(firstName, person.firstName) &&
                    Objects.equals(lastName, person.lastName) &&
                    Objects.equals(patronymic, person.patronymic) &&
                    Objects.equals(address, person.address) &&
                    Objects.equals(contact, person.contact) &&
                    Objects.equals(birthDate, person.birthDate);
        }
        return false;
    }

    @Override
    public String toString() {
        return """
            Person[firstName=%s, \
            lastName=%s, \
            patronymic=%s, \
            bithDate=%s, \
            address=%s, \
            contact=%s\
            ]\
            """.formatted(firstName, 
                    lastName, patronymic,
                    birthDate, address.toString(), 
                        contact.toString());
    }
}
