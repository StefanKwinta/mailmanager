package com.springprojects.mailmanager.mail;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@ContextConfiguration
public class MailInboxTests {
    @Test
    public void inboxTest(){
        //MailInbox.getInbox("test@localhost","test");
    }


}