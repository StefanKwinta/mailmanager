package com.springprojects.mailmanager.network;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class MailSendingSessionEstablisher {
    public static Session getSession(String login,String password){

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", HostAssigner.assignSMTPHost(login));
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(login, password);

            }

        });

        return session;
    }
}
