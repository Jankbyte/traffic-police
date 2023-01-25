package ru.jankbyte.trafficpolice.model.entity.auth;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;
import ru.jankbyte.trafficpolice.model.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Table(name = "roles",
    schema = "auth_info")
@Setter
@Getter
public class Role extends BaseEntity {
    @NotNull
    @Enumerated(EnumType.STRING)
    private Roles name;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public Role() {}

    public Role(Roles name) {
        this.name = name;
    }

    public Role(Roles name, Long id) {
        this(name);
        setId(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof Role role) {
            return Objects.equals(name, role.name);
        }
        return false;
    }
}
