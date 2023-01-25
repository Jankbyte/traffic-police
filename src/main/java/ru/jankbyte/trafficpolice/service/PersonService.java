package ru.jankbyte.trafficpolice.service;

import ru.jankbyte.trafficpolice.model.ResultPage;
import ru.jankbyte.trafficpolice.model.entity.Person;
import java.util.List;

/**
 * Представление сервиса для управления участниками.
 */
public interface PersonService {
    /**
     * Найти участника по номеру телефона.
     * @param phoneNumber Номер телефона
     * @return Данные участника с таким номером
     */
    public Person getByPhoneNumber(String phoneNumber);

    /**
     * Найти участника по электронной почте.
     * @param email Электронная почта
     * @return Участник с данной почтой
     */
    public Person getByEmail(String email);

    /**
     * Получить участника с нужным ID.
     * @param id Уникальной номер участника (ID)
     * @return Участник с нужным номером
     */
    public Person getById(Long id);

    /**
     * Получить список всех участников.
     * @return Список участников
     */
    public List<Person> getAll();

    /**
     * Получить список участников в виде страниц.
     * @param page номер страницы. Внимание: первая страница
     * будет начинатся с единицы, а не с нуля
     * @return страница с участниками
     */
    public ResultPage<Person> getAll(int page);

    /**
     * Удалить участника с по его ID.
     * @param id Уникальный номер участника
     */
    public void removeById(Long id);

    /**
     * Сохраняет участника.
     * @param person объект для сохранения
     * @return сохранённый объект с ID
     */
    public Person save(Person person);

    /**
     * Обновляет участника.
     * @param id номер участника, которого нужно обновить
     * @param person объект, из которого возьмутся данные
     * для вставки
     * @return обновлённый участник
     */
    public Person update(Long id, Person person);

    /**
     * Получить кол-во участников.
     * @return кол-во участников
     */
    public long getCount();
}
