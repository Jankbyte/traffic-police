package ru.jankbyte.trafficpolice.service.car;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.assertj.core.api.Assertions.assertThat;

import ru.jankbyte.trafficpolice.service.CarService;
import ru.jankbyte.trafficpolice.service.PersonService;
import ru.jankbyte.trafficpolice.service.ViolationService;
import ru.jankbyte.trafficpolice.service.impl.CarServiceImpl;
import ru.jankbyte.trafficpolice.repository.CarRepository;

import ru.jankbyte.trafficpolice.model.entity.Car;

import static java.util.Optional.ofNullable;

@ExtendWith(MockitoExtension.class)
public class UpdateCarStatementsTest {
    @Mock
    private CarRepository repository;

    @Mock
    private PersonService persons;

    @Mock
    private ViolationService violations;

    private CarService service;

    @BeforeEach
    public void initService() {
        validateMocking();
        service = new CarServiceImpl(
            repository, persons, violations);
    }

    private void validateMocking() {
        assertThat(repository).isNotNull();
        assertThat(persons).isNotNull();
        assertThat(violations).isNotNull();
    }
}
