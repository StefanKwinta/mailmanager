package com.springprojects.mailmanager.network;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class MailSendingSessionEstablisherTests {
    @Test
    public void sessionNotNull(){
        assertThat(MailSendingSessionEstablisher.getSession("","")).isNotNull();
    }
}
