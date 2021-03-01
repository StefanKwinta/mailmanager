package com.springprojects.mailmanager.security;

import com.springprojects.mailmanager.network.MailSendingSessionEstablisher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PasswordEncryptorTests {
    @Test
    public void encryptDecrypt(){
        String password = "137h8yfsd8y23yuvbuf";
        PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
        assert(passwordEncryptor.decrypt(passwordEncryptor.encrypt("137h8yfsd8y23yuvbuf")).equals(password));
    }
}
