package ru.jankbyte.trafficpolice.controller.mvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import ru.jankbyte.trafficpolice.service.ViolationService;
import static ru.jankbyte.trafficpolice.util.MvcUtils.setPageTitle;

@Controller
@RequestMapping(value = {"/violation"},
    produces = MediaType.TEXT_HTML_VALUE)
public class ViolationController {
    private final ViolationService service;

    public ViolationController(ViolationService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public String getById(Model model,
            @PathVariable("id") Long id) {
        setPageTitle(model, "Информация о нарушении");
        prepareViolationForMvcById(id, model);
        return "violation";
    }

    @GetMapping({"/edit/", "/edit"})
    public String create(Model model) {
        setPageTitle(model, "Добавление нарушения");
        return "edit-violation";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model,
            @PathVariable("id") Long id) {
        setPageTitle(model, "Редактирование нарушения");
        prepareViolationForMvcById(id, model);
        return "edit-violation";
    }

    private void prepareViolationForMvcById(Long id, Model model) {
        var violation = service.getById(id);
        model.addAttribute("violation", violation);
    }
}
