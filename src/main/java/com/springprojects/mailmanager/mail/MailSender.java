package com.springprojects.mailmanager.mail;

import com.springprojects.mailmanager.network.MailSendingSessionEstablisher;
import com.springprojects.mailmanager.model.MailContent;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
    public static void sendMail(String login, String password, String sendTo, MailContent mailContent){
        Session session = MailSendingSessionEstablisher.getSession(login, password);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailContent.getFrom()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
            message.setSubject(mailContent.getSubject());
            message.setText(mailContent.getContent());
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
