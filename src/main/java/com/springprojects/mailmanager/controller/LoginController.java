package com.springprojects.mailmanager.controller;

import com.springprojects.mailmanager.mail.SendMail;
import com.springprojects.mailmanager.security.LoginData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getMapingMailManager(@ModelAttribute LoginData logindata, Model model) {
        model.addAttribute("logindata", new LoginData());
        return "login";
    }
    @PostMapping("/login")
    public String postMapingMailManager(@ModelAttribute LoginData logindata, Model model) {
        SendMail.send(logindata.getLogin(),logindata.getPassword(),logindata.getAddress());
        model.addAttribute("logindata", logindata);
        return "login";
    }
}
