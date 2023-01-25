package ru.jankbyte.trafficpolice.repository;

import ru.jankbyte.trafficpolice.model.entity.auth.Role;
import ru.jankbyte.trafficpolice.model.entity.auth.Roles;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Repository
public interface RoleRepository
        extends JpaRepository<Role,Long> {
    @Transactional(readOnly = true)
    public Optional<Role> findByName(Roles name);
}
