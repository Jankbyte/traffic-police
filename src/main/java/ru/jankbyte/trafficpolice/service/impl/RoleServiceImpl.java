package ru.jankbyte.trafficpolice.service.impl;

import ru.jankbyte.trafficpolice.repository.RoleRepository;
import ru.jankbyte.trafficpolice.model.entity.auth.*;
import ru.jankbyte.trafficpolice.service.RoleService;
import ru.jankbyte.trafficpolice.exception.EntityNotFoundException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> getAll() {
        return repository.findAll();
    }

    @Override
    public Role getByName(Roles name) {
        return repository.findByName(name)
            .orElseThrow(() ->
                new EntityNotFoundException("Роль", "именем", name));
    }

    @Override
    public Role getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException("Роль", "номером", id));
    }
}
