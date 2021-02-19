package com.springprojects.mailmanager.controller;

import com.springprojects.mailmanager.mail.*;
import com.springprojects.mailmanager.security.LoginData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getMapingMailManager(@ModelAttribute LoginData logindata, Model model) {
        model.addAttribute("logindata", new LoginData());
        model.addAttribute("subjects", "");
        return "login";
    }
    @PostMapping("/login")
    public String postMapingMailManager(@ModelAttribute LoginData logindata, Model model,
                                        @RequestParam(value = "send", required = false) String send,
                                        @RequestParam(value = "fetch", required = false) String fetch) {
        if(fetch != null && fetch.equals("Fetch")){
            model.addAttribute("subjects", EmailRecive.read(logindata.getLogin(),logindata.getPassword()));
        }else if(fetch != null && send.equals("Send")){
            SendMail.send(logindata.getLogin(),logindata.getPassword(),logindata.getAddress());
        }
        model.addAttribute("logindata", logindata);
        return "login";
    }
}
