package ru.jankbyte.trafficpolice.exception;

/**
 * Исключение вызывается в случае отсутствия сущности.
 * <p>К примеру, сущность была не найдена по ID, имени, номеру.</p>
 */
public class EntityNotFoundException
        extends TrafficPoliceException {
    /**
     * Создание исключение с описанием.
     * @param entityName Имя сущности
     * @param paramName Имя параметра, по которому сущность была не найдена
     * @param value Значение, которое дожен был содержать параметр (paramName)
     */
    public EntityNotFoundException(String entityName,
            String paramName, Object value) {
        super("%s с %s \"%s\" не найден"
            .formatted(entityName, paramName, value));
    }
}
