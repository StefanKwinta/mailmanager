package com.springprojects.mailmanager.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class LoginControllerTests {
    @Autowired
    private LoginController loginController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(loginController).isNotNull();
    }
}
