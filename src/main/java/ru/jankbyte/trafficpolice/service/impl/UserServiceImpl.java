package ru.jankbyte.trafficpolice.service.impl;

import ru.jankbyte.trafficpolice.repository.UserRepository;
import ru.jankbyte.trafficpolice.service.UserService;
import ru.jankbyte.trafficpolice.service.RoleService;
import ru.jankbyte.trafficpolice.model.entity.auth.*;
import ru.jankbyte.trafficpolice.model.ResultPage;
import ru.jankbyte.trafficpolice.exception.EntityNotFoundException;
import static ru.jankbyte.trafficpolice.util.PageableUtils.getPageable;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final RoleService service;

    public UserServiceImpl(UserRepository repository, 
            RoleService service, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
        this.service = service;
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public ResultPage<User> getAll(int pageNumber) {
        var pageable = getPageable(pageNumber);
        var page = repository.findAll(pageable);
        return new ResultPage<User>(page);
    }

    @Override
    public User getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException("Пользователь", "номером", id));
    }

    @Override
    public User getByName(String name) {
        return repository.findByName(name)
            .orElseThrow(() ->
                new EntityNotFoundException("Пользователь", "именем", name));
    }

    @Override
    public User update(Long id, User jsonData) {
        var user = getById(id);
        updateUser(user, jsonData);
        return repository.save(user);
    }

    @Override
    public User save(User user) {
        setRole(user, user.getRole());
        return repository.save(user);
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void removeByName(String name) {
        repository.deleteByName(name);
    }

    private void updateUser(User user, User newUser) {
        user.setName(newUser.getName());
        user.setEnabled(newUser.getEnabled());
        setRole(user, newUser.getRole());
    }

    private void setRole(User user, Role currentRole) {
        if (currentRole.getName() != null) {
            var role = service.getByName(currentRole.getName());
            user.setRole(role);
        }
    }
}
