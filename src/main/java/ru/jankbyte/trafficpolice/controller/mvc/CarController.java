package ru.jankbyte.trafficpolice.controller.mvc;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import ru.jankbyte.trafficpolice.service.CarService;
import org.springframework.http.MediaType;
import ru.jankbyte.trafficpolice.util.MvcUtils;

@Controller
@RequestMapping(value = {"/car"},
    produces = MediaType.TEXT_HTML_VALUE)
public class CarController {
    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping
    public String getAll(Model model,
            @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        var page = service.getAll(pageNumber);
        MvcUtils.setPageable(model, page, "car");
        MvcUtils.setPageTitle(model, "Список автомобилей");
        model.addAttribute("cars", page.getElements());
        return "cars";
    }

    @GetMapping("/{id}")
    public String getById(Model model,
            @PathVariable("id") Long id) {
        MvcUtils.setPageTitle(model, "Информация об автомобиле");
        prepareCarForMvcById(id, model);
        return "car";
    }

    @GetMapping({"/edit/", "/edit"})
    public String create(Model model) {
        MvcUtils.setPageTitle(model, "Добавление автомобиля");
        return "edit-car";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model,
            @PathVariable("id") Long id) {
        MvcUtils.setPageTitle(model, "Редактирование автомобиля");
        prepareCarForMvcById(id, model);
        return "edit-car";
    }

    private void prepareCarForMvcById(Long id, Model model) {
        var car = service.getById(id);
        model.addAttribute("car", car);
    }
}
