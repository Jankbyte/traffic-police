package ru.jankbyte.trafficpolice.model;
/**
 * Модель-представление получения кол-ва
 * объектов через REST-запросы.
 * <p>Если нужно вернуть количество сущностей (к
 * примеру - через методы <code>service.getCount()</code>), 
 * то можно использовать данный класс в качестве заготовки для REST-ответа:</p>
 * <pre>{@code
 *     @GetMapping
 *     public EntityCount getAll() {
 *         long count = personService.getCount();
 *         return new EntityCount(count);
 *     }
 * }</pre>
 */
public final record EntityCount(Long count) {
}
