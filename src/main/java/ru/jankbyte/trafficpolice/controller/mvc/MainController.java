package ru.jankbyte.trafficpolice.controller.mvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.context.SecurityContextHolder;

import ru.jankbyte.trafficpolice.service.AuthorityService;
import static ru.jankbyte.trafficpolice.util.MvcUtils.setPageTitle;

@Controller
@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
public class MainController {
    private final AuthorityService authority;

    public MainController(AuthorityService authority) {
        this.authority = authority;
    }

    @GetMapping("/")
    public String index(Model model) {
        setPageTitle(model, "Панель управление");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        setPageTitle(model, "Авторизация");
        return "login";
    }

    @GetMapping("/me")
    public String me(Model model) {
        setPageTitle(model, "Личный кабинет");
        var user = authority.getUser();
        model.addAttribute("user", user);
        return "user";
    }
}
