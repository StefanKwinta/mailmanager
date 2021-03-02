package com.springprojects.mailmanager.mail;

import com.springprojects.mailmanager.model.MailContent;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ContextConfiguration
public class MailInboxTests {
    @Test
    public void inboxTest(){
        //List<MailContent> content= MailInbox.getInbox("test@localhost","test");
        //assert(content.size() == 1);
    }


}