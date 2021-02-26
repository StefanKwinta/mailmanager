package com.springprojects.mailmanager.exceptions;

import com.springprojects.mailmanager.data.MailAccountModelAssembler;
import com.springprojects.mailmanager.data.MailAccountsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MailAccountNotFoundExceptionTests {
    @Autowired
    MailAccountsRepository mailAccountsRepository;
    @Autowired
    MailAccountModelAssembler mailAccountModelAssembler;

    @Test
    public void exceptionThrownTest() {
        Exception exception = assertThrows(MailAccountNotFoundException.class, () -> {
            mailAccountsRepository.findById("NotExisting")
                    .orElseThrow(() -> new MailAccountNotFoundException("NotExisting"));
        });

        String expectedMessage = "Could not find mail account NotExisting";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
