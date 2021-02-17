package com.springprojects.mailmanager.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailManagerController {
    @GetMapping("/mailmanager")
    public String getMapingMailManager() {

        return "mailmanager";
    }
}
