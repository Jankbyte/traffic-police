package ru.jankbyte.trafficpolice.service.impl;

import ru.jankbyte.trafficpolice.repository.PersonRepository;
import ru.jankbyte.trafficpolice.exception.EntityNotFoundException;
import ru.jankbyte.trafficpolice.model.entity.*;
import ru.jankbyte.trafficpolice.model.ResultPage;
import ru.jankbyte.trafficpolice.service.PersonService;
import ru.jankbyte.trafficpolice.service.ViolationService;
import static ru.jankbyte.trafficpolice.util.PageableUtils.getPageable;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository repository;
    private final ViolationService violationService;

    public PersonServiceImpl(PersonRepository repository,
            ViolationService violationService) {
        this.repository = repository;
        this.violationService = violationService;
    }

    @Override
    public Person getByPhoneNumber(String phoneNumber) {
        var person =
            repository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                    new EntityNotFoundException(
                        "Участник", "номером телефона", phoneNumber));
        return person;
    }

    @Override
    public Person getByEmail(String email) {
        var person =
            repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                    "Участник", "с почтой", email));
        return person;
    }

    @Override
    public Person getById(Long id) {
        var person = repository.findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException("Участник", "номером", id));
        return person;
    }

    @Override
    public List<Person> getAll() {
        return repository.findAll();
    }

    @Override
    public ResultPage<Person> getAll(int pageNumber) {
        var pageable = getPageable(pageNumber);
        var page = repository.findAll(pageable);
        return new ResultPage<Person>(page);
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        violationService.removeByViolatorId(id);
        repository.deleteById(id);
    }

    @Override
    public long getCount() {
        return repository.count();
    }

    @Override
    public Person save(Person person) {
        return repository.save(person);
    }

    @Override
    public Person update(Long id, Person newPerson) {
        var person = getById(id);
        updatePerson(person, newPerson);
        updateAddress(person.getAddress(), newPerson.getAddress());
        updateContact(person.getContact(), newPerson.getContact());
        return repository.save(person);
    }

    private void updateAddress(Address address, Address newAddress) {
        address.setCity(newAddress.getCity());
        address.setStreet(newAddress.getStreet());
        address.setHouse(newAddress.getHouse());
        address.setFlat(newAddress.getFlat());
    }

    private void updateContact(Contact contact, Contact newContact) {
        contact.setEmail(newContact.getEmail());
        contact.setPhoneNumber(newContact.getPhoneNumber());
    }

    private void updatePerson(Person person, Person newPerson) {
        person.setFirstName(newPerson.getFirstName());
        person.setLastName(newPerson.getLastName());
        person.setPatronymic(newPerson.getPatronymic());
        person.setBirthDate(newPerson.getBirthDate());
    }
}