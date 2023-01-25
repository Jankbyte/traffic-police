package ru.jankbyte.trafficpolice.controller.mvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import ru.jankbyte.trafficpolice.service.UserService;
import ru.jankbyte.trafficpolice.util.MvcUtils;

@Controller
@RequestMapping(value = {"/user"},
    produces = MediaType.TEXT_HTML_VALUE)
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAll(Model model,
            @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        var page = userService.getAll(pageNumber);
        MvcUtils.setPageable(model, page, "user");
        MvcUtils.setPageTitle(model, "Список пользователей");
        model.addAttribute("users", page.getElements());
        return "users";
    }

    @GetMapping({"/edit", "/edit/"})
    public String create(Model model) {
        MvcUtils.setPageTitle(model, "Новый пользователь");
        return "edit-user";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        MvcUtils.setPageTitle(model, "Редактирование пользователя");
        var user = userService.getById(id);
        model.addAttribute("user", user);
        return "edit-user";
    }

    @GetMapping("/{id}")
    public String getById(Model model,
            @PathVariable("id") Long id) {
        MvcUtils.setPageTitle(model, "Информация о пользователе");
        var user = userService.getById(id);
        model.addAttribute("user", user);
        return "user";
    }
}
