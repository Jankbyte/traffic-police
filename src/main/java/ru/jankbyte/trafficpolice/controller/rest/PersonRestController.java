package ru.jankbyte.trafficpolice.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import ru.jankbyte.trafficpolice.model.entity.Person;
import ru.jankbyte.trafficpolice.service.PersonService;
import ru.jankbyte.trafficpolice.model.EntityCount;
import ru.jankbyte.trafficpolice.model.ResultPage;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = {"/api/person"},
    consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
public class PersonRestController {
    private final PersonService service;

    public PersonRestController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/count")
    public EntityCount getCount() {
        long count = service.getCount();
        return new EntityCount(count);
    }

    @GetMapping
    public ResultPage<Person> getAllByPagable(
            @RequestParam(defaultValue = "1") int page) {
        return service.getAll(page);
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @PutMapping({"", "/"})
    public Person save(@Valid @RequestBody Person person) {
        return service.save(person);
    }

    @PostMapping("/{id}")
    public Person update(@PathVariable("id") Long id,
            @Valid @RequestBody Person person) {
        return service.update(id, person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeById(@PathVariable("id") Long id) {
        service.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
