package com.springprojects.mailmanager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {
    @GetMapping("/")
    public RedirectView redirectWithUsingRedirectView() {
        return new RedirectView("/home");
    }
    @GetMapping("/home")
    public String getMapingHome() {
        return "home";
    }
}
