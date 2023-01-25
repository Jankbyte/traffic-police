package ru.jankbyte.trafficpolice.controller.mvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import ru.jankbyte.trafficpolice.service.PersonService;
import ru.jankbyte.trafficpolice.util.MvcUtils;

@Controller
@RequestMapping(value = {"/person"},
    produces = MediaType.TEXT_HTML_VALUE)
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getAll(Model model,
            @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        var page = personService.getAll(pageNumber);
        MvcUtils.setPageable(model, page, "person");
        MvcUtils.setPageTitle(model, "Список участников");
        model.addAttribute("persons", page.getElements());
        return "persons";
    }

    @GetMapping("/edit/{id}")
    public String getAll(@PathVariable("id") Long id, Model model) {
        MvcUtils.setPageTitle(model, "Редактирование участника");
        preparePersonForMvcById(model, id);
        return "edit-person";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        MvcUtils.setPageTitle(model, "Информация об участнике");
        preparePersonForMvcById(model, id);
        return "person";
    }

    @GetMapping({"/edit", "/edit/"})
    public String create(Model model) {
        MvcUtils.setPageTitle(model, "Создание участника");
        return "edit-person";
    }

    private void preparePersonForMvcById(Model model, Long id) {
        var person = personService.getById(id);
        model.addAttribute("person", person);
    }
}
