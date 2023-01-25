package ru.jankbyte.trafficpolice.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import ru.jankbyte.trafficpolice.model.entity.Car;
import ru.jankbyte.trafficpolice.repository.CarRepository;
import ru.jankbyte.trafficpolice.Main;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private CarRepository repository;

    @Test
    public void shouldFindByOwnerId() {
        var cars = repository.findByOwnerId(1L);
        assertThat(cars).hasSize(2);
        cars = repository.findByOwnerId(1000L);
        assertThat(cars).isEmpty();
    }
}
