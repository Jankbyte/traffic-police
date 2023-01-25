package ru.jankbyte.trafficpolice.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import ru.jankbyte.trafficpolice.service.AuthorityService;
import ru.jankbyte.trafficpolice.service.UserService;
import ru.jankbyte.trafficpolice.model.entity.auth.User;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    private final UserService service;

    public AuthorityServiceImpl(UserService service) {
        this.service = service;
    }

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext()
            .getAuthentication();
    }

    @Override
    public User getUser() {
        var name = getAuthentication().getName();
        return service.getByName(name);
    }
}
