package com.springprojects.mailmanager.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class MailManagerControllerTests {
    @Autowired
    private MailManagerController mailManagerController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(mailManagerController).isNotNull();
    }
}
