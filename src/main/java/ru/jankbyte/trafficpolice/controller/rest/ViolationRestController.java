package ru.jankbyte.trafficpolice.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import ru.jankbyte.trafficpolice.service.CarService;
import ru.jankbyte.trafficpolice.service.ViolationService;
import ru.jankbyte.trafficpolice.model.entity.Violation;
import ru.jankbyte.trafficpolice.model.EntityCount;

import java.util.List;

@RestController
@RequestMapping(value = "/api/violation",
    consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
public class ViolationRestController {
    private final ViolationService violationService;
    private final CarService carService;

    public ViolationRestController(CarService carService,
            ViolationService violationService) {
        this.carService = carService;
        this.violationService = violationService;
    }

    @GetMapping
    public Iterable<Violation> getByCarId(
            @RequestParam(value = "car-id",
                required = true) Long carId,
            @RequestParam(value = "active",
                required = false) Boolean active) {
        var result = active == null ?
                violationService.getByCarId(carId) :
                violationService.getActiveByCarId(active, carId);
        return result;
    }

    @PutMapping("/{id}")
    public Violation addForCar(
            @RequestBody Violation violation, 
            @PathVariable("id") Long carId) {
        return carService.addViolation(carId, violation);
    }

    @PostMapping("/{id}")
    public Violation update(
            @RequestBody Violation violation, 
            @PathVariable("id") Long id) {
        return violationService.update(id, violation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeById(@PathVariable("id") Long id) {
        violationService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
