package ru.jankbyte.trafficpolice.model.entity;

import java.util.Objects;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "violations",
    schema = "cars_info")
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"id", "message", "active",
    "time", "car", "violator"})
public class Violation extends BaseEntity {
    @NotNull(message = "Укажите время нарушение")
    @Basic
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+3")
    private Timestamp time;

    @NotBlank(message = "Укажите причину штрафа")
    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Boolean active;

    @NotNull(message = "Укажите автомобиль с ID")
    @ManyToOne(optional = false)
    private Car car;

    @ManyToOne(optional = false)
    private Person violator;

    @Override
    public int hashCode() {
        return Objects.hash(time,
            message, active, car, violator);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof Violation viol) {
            return Objects.equals(time, viol.time) &&
                    Objects.equals(message, viol.message) &&
                    Objects.equals(active, viol.active) &&
                    Objects.equals(car, viol.car) &&
                    Objects.equals(violator, viol.violator);
        }
        return false;
    }
}
