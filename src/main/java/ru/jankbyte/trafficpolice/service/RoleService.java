package ru.jankbyte.trafficpolice.service;

import ru.jankbyte.trafficpolice.model.entity.auth.Role;
import ru.jankbyte.trafficpolice.model.entity.auth.Roles;
import java.util.List;

/**
 * Представление сервиса для управления
 * ролями пользователя.
 */
public interface RoleService {
    /**
     * Получить список всех ролей.
     * @return коллекция с ролями
     */
    public List<Role> getAll();

    /**
     * Получить роль по имени.
     * @param name Имя роли
     * @return объект роли
     */
    public Role getByName(Roles name);

    /**
     * Получить роль по номеру.
     * @param id Уникальный номер роли
     * @return объект роли
     */
    public Role getById(Long id);
}
