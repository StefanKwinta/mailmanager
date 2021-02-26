package com.springprojects.mailmanager.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class HomeControllerTests {
    @Autowired
    private HomeController homeController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(homeController).isNotNull();
    }
}
