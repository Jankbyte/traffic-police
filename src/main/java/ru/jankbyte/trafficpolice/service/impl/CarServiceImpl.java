package ru.jankbyte.trafficpolice.service.impl;

import ru.jankbyte.trafficpolice.repository.CarRepository;
import ru.jankbyte.trafficpolice.model.entity.*;
import ru.jankbyte.trafficpolice.model.ResultPage;
import ru.jankbyte.trafficpolice.exception.EntityNotFoundException;
import ru.jankbyte.trafficpolice.exception.CarChangeOwnerException;
import ru.jankbyte.trafficpolice.service.CarService;
import ru.jankbyte.trafficpolice.service.PersonService;
import ru.jankbyte.trafficpolice.service.ViolationService;
import static ru.jankbyte.trafficpolice.util.PageableUtils.getPageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository repository;
    private final PersonService personService;
    private final ViolationService violationService;

    public CarServiceImpl(CarRepository repository,
            PersonService personService,
            ViolationService violationService) {
        this.violationService = violationService;
        this.repository = repository;
        this.personService = personService;
    }

    @Override
    public Violation addViolation(Long id, Violation violation) {
        var car = getById(id);
        return violationService.save(car, violation);
    }

    @Override
    public long getCount() {
        return repository.count();
    }

    @Override
    public List<Car> getAll() {
        return repository.findAll();
    }

    @Override
    public ResultPage<Car> getAll(int pageNumber) {
        var pageable = getPageable(pageNumber);
        var page = repository.findAll(pageable);
        return new ResultPage<Car>(page);
    }

    @Override
    public List<Car> getByOwnerId(Long ownerId) {
        return repository.findByOwnerId(ownerId);
    }

    @Override
    public Car getById(Long id) {
        var car = repository.findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "Машина", "уникальным номером", id));
        return car;
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        violationService.removeByCarId(id);
        repository.deleteById(id);
    }

    @Override
    public Car save(Car car) {
        var owner = car.getOwner();
        if (owner != null) {
            setCarOwner(car, owner);
        }
        return repository.save(car);
    }

    @Override
    public Car update(Long id, Car newCar) {
        var car = getById(id);
        updateCar(car, newCar);
        return repository.save(car);
    }

    private void updateCar(Car car, Car newCar) {
        car.setBrand(newCar.getBrand());
        car.setModel(newCar.getModel());
        car.setNumber(newCar.getNumber());
        car.setColor(newCar.getColor());
        changeOwner(car, newCar);
    }

    private void changeOwner(Car car, Car newCar) {
        var owner = newCar.getOwner();
        if (owner != null) {
            if (isHasActiveViolations(car) &&
                    !isSameOwnersId(car.getOwner(), owner)) {
                throw new CarChangeOwnerException();
            }
            setCarOwner(car, owner);
        }
    }

    private void setCarOwner(Car car, Person owner) {
        var id = owner.getId();
        if (id != null) {
            var person = personService.getById(id);
            car.setOwner(person);
            return;
        }
        car.setOwner(null);
    }

    private boolean isSameOwnersId(
            Person owner,Person newOwner) {
        return owner.getId().equals(newOwner.getId());
    }

    private boolean isHasActiveViolations(Car car) {
        return !violationService.getActiveByCarId(
            true, car.getId()).isEmpty();
    }
}
