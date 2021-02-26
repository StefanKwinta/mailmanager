package com.springprojects.mailmanager.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MailAccountNotFoundAdviceTests {


    @Test
    public void handleFunctionTest() {

        MailAccountNotFoundAdvice mailAccountNotFoundAdvice = new MailAccountNotFoundAdvice();
        mailAccountNotFoundAdvice.employeeNotFoundHandler(new MailAccountNotFoundException("TEST"));
        assert(mailAccountNotFoundAdvice.employeeNotFoundHandler(new MailAccountNotFoundException("TEST")).equals("Could not find mail account TEST"));


    }
}
