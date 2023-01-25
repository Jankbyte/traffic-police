package ru.jankbyte.trafficpolice.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import ru.jankbyte.trafficpolice.service.CarService;
import ru.jankbyte.trafficpolice.model.entity.Car;
import ru.jankbyte.trafficpolice.model.EntityCount;
import ru.jankbyte.trafficpolice.model.ResultPage;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/car",
    consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
public class CarRestController {
    private final CarService service;

    public CarRestController(CarService service) {
        this.service = service;
    }

    @GetMapping
    public ResultPage<Car> getAllByPagable(
            @RequestParam(defaultValue = "1") int page) {
        return service.getAll(page);
    }

    @GetMapping("/count")
    public EntityCount getCount() {
        long count = service.getCount();
        return new EntityCount(count);
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @PutMapping({"", "/"})
    public Car save(@Valid @RequestBody Car car) {
        return service.save(car);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeById(
            @PathVariable("id") Long id) {
        service.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public Car update(@Valid @RequestBody Car car,
            @PathVariable("id") Long id) {
        return service.update(id, car);
    }
}
