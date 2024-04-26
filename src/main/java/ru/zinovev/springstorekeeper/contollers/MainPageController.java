package ru.zinovev.springstorekeeper.contollers;

import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zinovev.springstorekeeper.services.UserService;

@Controller
@RequestMapping("/")
public class MainPageController {

    @GetMapping()
    public String begin() {

        return "mainpage";
    }

}
