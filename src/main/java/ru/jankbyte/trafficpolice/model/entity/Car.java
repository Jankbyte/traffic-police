package ru.jankbyte.trafficpolice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

import java.sql.Date;

import ru.jankbyte.trafficpolice.validator.annotation.CarNumber;

import lombok.*;

@Entity
@Table(name = "cars",
    schema = "cars_info")
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"id", "brand", "model",
    "number", "color", "created", "owner"})
public class Car extends BaseEntity {
    @NotBlank(message = "Производитель не указан")
    @Column(nullable = false)
    private String brand;

    @NotBlank(message = "Модель не указана")
    @Column(nullable = false)
    private String model;

    @CarNumber(message = "Неверный формат номера машины")
    @Column(name = "register_number",
        nullable = false, unique = false)
    private String number;

    @NotBlank(message = "Цвет не указан")
    @Column(nullable = false)
    private String color;

    @NotNull(message = "Не указана дата выпуска")
    @Basic
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd", timezone = "GMT+3")
    private Date created;

    @ManyToOne
    private Person owner;

    @OneToMany(cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id",
        referencedColumnName = "id")
    @JsonIgnore
    @Builder.Default
    private Set<Violation> violations = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(
            color, model, brand, number);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (object instanceof Car car) {
            return Objects.equals(color, car.color) &&
                    Objects.equals(model, car.model) &&
                    Objects.equals(number, car.number) &&
                    Objects.equals(brand, car.brand);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Car(color=%s, model=%s, brand=%s, number=%s, owner=%s)"
            .formatted(color, model, brand, number, owner);
    }
}
