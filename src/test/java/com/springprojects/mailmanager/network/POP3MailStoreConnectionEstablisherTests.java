package com.springprojects.mailmanager.network;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class POP3MailStoreConnectionEstablisherTests {
    @Test
    public void storeNotNull(){
        assertThat(new POP3MailStoreConnectionEstablisher().getStore("","")).isNotNull();
    }
}
