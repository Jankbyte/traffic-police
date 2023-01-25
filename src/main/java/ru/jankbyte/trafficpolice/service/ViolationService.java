package ru.jankbyte.trafficpolice.service;

import ru.jankbyte.trafficpolice.model.entity.Car;
import ru.jankbyte.trafficpolice.model.entity.Violation;
import java.util.List;

/**
 * Сервис отвечающий за работу с нарушениями.
 * <p>Позволяет: добавлять, получать и обновлять нарушения у автомобиля.</p>
 */
public interface ViolationService {
    /**
     * Получить список нарушений, связанные с автомобилем.
     * @param carId Уникальный номер автомобиля
     * @return Список нарушений
     */
    public List<Violation> getByCarId(Long carId);

    /**
     * Получить список нарушений участника по его ID.
     * @param id Уникальный номер участника
     * @return Список нарушений, которые числятся за участником
     */
    public List<Violation> getByViolatorId(Long id);

    /**
     * Получить список не/активных нарушений, связанных
     * с автомобилем.
     * @param active Если true - то активные
     * @param carId Уникальный номер автомобиля
     * @return Список не/активных нарушений
     */
    public List<Violation> getActiveByCarId(
        Boolean active, Long carId);

    /**
     * Получить список не/активных нарушений, связанных
     * с участником.
     * @param active Если true - то активные
     * @param violatorId Уникальный номер участника
     * @return Список не/активных нарушений
     */
    public List<Violation> getActiveByViolatorId(
        Boolean active, Long violatorId);

    /**
     * Удалить все нарушения, связанные с автомобилем.
     * @param carId Уникальный номер автомобиля
     */
    public void removeByCarId(Long carId);

    /**
     * Удалить все нарушения, связанные с нарушителем.
     * @param violatorId Уникальный номер нарушителя
     */
    public void removeByViolatorId(Long violatorId);

    /**
     * Сохраняет и закрепляет нарушение за автомобилем.
     * @param car Экземпляр автомобиля с указанным ID, за которым
     * будет закрепленно нарушение.
     * @param violation Представление нарушения с данными, которые
     * будут привязанны к автомобилю
     * @return Сохранённое нарушение с новым ID
     */
    public Violation save(Car car, Violation violation);

    /**
     * Получить данные о нарушении по его уникальному номеру.
     * @param id Уникальный номер нарушения
     * @return Данные о нарушении
     */
    public Violation getById(Long id);

    /**
     * Обновляет нарушение по уникальному номеру.
     * @param id Уникальный номер
     * @param violation Данные о нарушении, которые будут
     * заменены
     * @return Обновлённое нарушение
     */
    public Violation update(Long id, Violation violation);

    /**
     * Удаляет нарушение по уникальному номеру.
     * @param id Уникальный номер
     */
    public void removeById(Long id);
}
