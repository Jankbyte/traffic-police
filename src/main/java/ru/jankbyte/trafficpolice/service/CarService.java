package ru.jankbyte.trafficpolice.service;

import java.util.List;
import ru.jankbyte.trafficpolice.model.entity.Car;
import ru.jankbyte.trafficpolice.model.entity.Violation;
import ru.jankbyte.trafficpolice.model.ResultPage;
import org.springframework.data.domain.Pageable;

/**
 * Реализует бизнес-логику
 * для управления автомобилями.
 */
public interface CarService {
    /**
     * Получить список всех автомобилей.
     * @return Коллекция из автомобилей.
     */
    public List<Car> getAll();

    /**
     * Получить список автомобилий в виде страниц.
     * @param page номер страницы. Внимание: первая страница
     * будет начинатся с единицы, а не с нуля
     * @return страница с автомобилями
     */
    public ResultPage<Car> getAll(int page);

    /**
     * Получить список автомобилей у определённого участника
     * ({@link ru.jankbyte.trafficpolice.model.entity.Person}).
     * @param ownerId Уникальный номер участника.
     * @return Список автомобилей участника
     */
    public List<Car> getByOwnerId(Long ownerId);

    /**
     * Получить сведенья об автомобиле по его
     * уникальному номеру (ID).
     * @param id Уникальный номер
     * @return Данные автомобиля
     */
    public Car getById(Long id);

    /**
     * Удалить автомобиль из базы
     * по его уникальному номеру (ID).
     * @param id Уникальный номер
     */
    public void removeById(Long id);

    /**
     * Добавляет автомобиль в базу.
     * @param car Данные автомобиля, которые будут сохранены
     * @return Тот-же самый объект, но с установленным ID
     */
    public Car save(Car car);

    /**
     * Обновить автомобиль в базе.
     * @param id Уникальный номер обновляемого автомобиля
     * @param car Данные автомобиля, которые будут установленны в обновляемый
     * автомобиль.
     * <p>Примечание: обновление владельца. Если при обновлении,
     * владелец отсутствует (null) - то остается прежний владелец. Если же
     * владелец есть, и у него есть номер (ID) - то он становится новым владельцем.
     * Если же - у владельца нету номера - то машина становится без владельца (owner = null)</p>
     * <p>Также, смена владельца не возможна - если у текущего владельца есть активные нарушения.</p>
     * @return Обновленные данные автомобиля
     */
    public Car update(Long id, Car car);

    /**
     * Получить кол-во всех автомобилей.
     * @return Кол-во автомобилей
     */
    public long getCount();

    /**
     * Добавляет нарушение на автомобиль по его
     * уникальному номеру.
     * @param id Уникальный номер
     * @param violation Данные о нарушении
     * @return Данные о нарушение
     */
    public Violation addViolation(
        Long id, Violation violation);
}
