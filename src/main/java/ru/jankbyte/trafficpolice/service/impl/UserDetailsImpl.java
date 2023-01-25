package ru.jankbyte.trafficpolice.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

import ru.jankbyte.trafficpolice.service.UserService;
import ru.jankbyte.trafficpolice.model.entity.auth.Role;

@Service
public class UserDetailsImpl
        implements UserDetailsService {
    private final UserService service;
    private final PasswordEncoder encoder;

    public UserDetailsImpl(UserService service,
            PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        var user = service.getByName(username);
        var password = encoder.encode(user.getPassword());
        return User.withUsername(user.getName())
            .password(password)
            .disabled(!user.getEnabled())
            .roles(user.getRole().getName().name())
            .build();
    }
}
