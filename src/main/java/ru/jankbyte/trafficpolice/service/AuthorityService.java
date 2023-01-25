package ru.jankbyte.trafficpolice.service;

import org.springframework.security.core.Authentication;
import ru.jankbyte.trafficpolice.model.entity.auth.User;

/**
 * Сервис, дающий информации об авторизованном
 * пользователе.
 */
public interface AuthorityService {
    /**
     * Возвращает объект аутентификации.
     * @return Авторизованная запись
     */
    public Authentication getAuthentication();

    /**
     * Возвращает текущего авторизованного
     * пользователя.
     * @return Авторизованный пользователь
     */
    public User getUser();
}
