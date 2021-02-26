package com.springprojects.mailmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class MailManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailManagerApplication.class, args);
    }

}
