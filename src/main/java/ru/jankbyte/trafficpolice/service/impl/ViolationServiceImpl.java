package ru.jankbyte.trafficpolice.service.impl;

import ru.jankbyte.trafficpolice.repository.ViolationRepository;
import ru.jankbyte.trafficpolice.model.entity.*;
import ru.jankbyte.trafficpolice.service.ViolationService;
import ru.jankbyte.trafficpolice.service.CarService;
import ru.jankbyte.trafficpolice.exception.EntityNotFoundException;
import ru.jankbyte.trafficpolice.exception.AddingViolationException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import jakarta.validation.Valid;

@Service
public class ViolationServiceImpl implements ViolationService {
    private final ViolationRepository repository;

    public ViolationServiceImpl(ViolationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Violation> getByCarId(Long carId) {
        return repository.findByCarId(carId);
    }

    @Override
    public List<Violation> getActiveByCarId(
            Boolean active, Long carId) {
        return repository.findActiveByCarId(
            active, carId);
    }

    @Override
    public List<Violation> getActiveByViolatorId(
            Boolean active, Long violatorId) {
        return repository.findActiveByViolatorId(
            active, violatorId);
    }

    @Override
    public List<Violation> getByViolatorId(Long id) {
        return repository.findByViolatorId(id);
    }

    @Override
    @Transactional
    public void removeByCarId(Long id) {
        repository.deleteByCarId(id);
        repository.flush();
    }

    @Override
    @Transactional
    public void removeByViolatorId(Long violatorId) {
        repository.deleteByViolatorId(violatorId);
        repository.flush();
    }

    @Override
    public Violation getById(Long id) {
        return repository.findById(id)
            .orElseThrow(
                ()-> new EntityNotFoundException("Штраф", "номером", id));
    }

    @Override
    public Violation save(Car car, Violation violation) {
        violation.setCar(car);
        var owner = car.getOwner();
        if (owner != null && owner.getId() != null) {
            violation.setViolator(owner);
            return repository.save(violation);
        }
        throw new AddingViolationException();
    }

    @Override
    public Violation update(Long id, Violation newViolation) {
        var violation = getById(id);
        violation.setMessage(newViolation.getMessage());
        violation.setActive(newViolation.getActive());
        violation.setTime(newViolation.getTime());
        return repository.save(violation);
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}
