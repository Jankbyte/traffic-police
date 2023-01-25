package ru.jankbyte.trafficpolice.model.entity.auth;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

import ru.jankbyte.trafficpolice.validator.annotation.Password;
import ru.jankbyte.trafficpolice.model.entity.BaseEntity;

import lombok.*;

@Entity
@Table(name = "users",
    schema = "auth_info")
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    @NotBlank(message = "Укажите имя")
    @Column(nullable = false, unique = true)
    private String name;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Password(message = "Пароль слишком простой")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "Укажите, активен ли пользователь")
    @Column(nullable = false)
    private Boolean enabled;

    @Valid
    @NotNull(message = "Необходимо указать роль пользователя")
    @ManyToOne(cascade = {
        CascadeType.DETACH, CascadeType.MERGE,
        CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinTable(name = "user_roles",
        schema = "auth_info",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role role;

    @Override
    public int hashCode() {
        return Objects.hash(name, password,
            enabled, role);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (object instanceof User user) {
            return Objects.equals(name, user.name) &&
                    Objects.equals(enabled, user.enabled) &&
                    Objects.equals(role, user.role);
        }
        return false;
    }
}