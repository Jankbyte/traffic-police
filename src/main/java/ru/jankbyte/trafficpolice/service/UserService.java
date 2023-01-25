package ru.jankbyte.trafficpolice.service;

import ru.jankbyte.trafficpolice.model.ResultPage;
import ru.jankbyte.trafficpolice.model.entity.auth.User;
import ru.jankbyte.trafficpolice.model.entity.auth.Roles;
import java.util.List;

/**
 * Сервис, отвечающий за работу с пользователями.
 * <p>Он позволяет управлять учётными записями пользователей,
 * которые управляют платформой - редакторы и администратор.</p>
 */
public interface UserService {
    /**
     * Получить список всех пользователей.
     * @return Список пользователей
     */
    public List<User> getAll();

    /**
     * Получить список пользователей в виде страниц.
     * @param page номер страницы. Внимание: первая страница
     * будет начинатся с единицы, а не с нуля
     * @return страница с пользователями
     */
    public ResultPage<User> getAll(int page);

    /**
     * Получить пользователя по его ID.
     * @param id Номер пользователя
     * @return Экземпляр пользователя с нужным ID
     */
    public User getById(Long id);

    /**
     * Сохраняет нового пользователя.
     * @return Тот же самый пользователь, но с сгенерированным ID
     */
    public User save(User user);

    /**
     * Ищет пользователя по имени.
     * @param name Имя пользователя
     * @return Экземпляр пользователя с нужным именем
     */
    public User getByName(String name);

    /**
     * Удалить пользователя по ID.
     * @param id Номер пользователя
     */
    public void removeById(Long id);

    /**
     * Удалить пользователя по имени.
     * @param name Имя пользователя
     */
    public void removeByName(String name);

    /**
     * Обновить пользователя с нужным ID.
     * @param id Уникальный номер
     * @param user Данные, которые запишутся в пользователя
     * @return Обновлённый пользователь
     */
    public User update(Long id, User user);
}
