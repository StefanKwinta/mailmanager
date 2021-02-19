package com.springprojects.mailmanager.mail;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;

import com.sun.mail.pop3.POP3Store;
import com.sun.mail.util.MailSSLSocketFactory;

public class EmailRecive {

    public static String read(String mailUser,String mailPassword) {

        String mailPop3Host = "pop.gmail.com";
        String mailStoreType = "pop3";

        return receiveEmail(mailPop3Host, mailStoreType, mailUser, mailPassword);
    }

    public static String receiveEmail(String pop3Host, String storeType, String user, String password) {
        String result = "";
        try {
            Properties properties = new Properties();
            properties.put("mail.pop3.host", pop3Host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.ssl.enable", "true");
            properties.put("mail.protocol.ssl.trust", "true");
            MailSSLSocketFactory socketFactory= new MailSSLSocketFactory();
            socketFactory.setTrustAllHosts(true);
            properties.put("mail.pop3.ssl.socketFactory", socketFactory);
            Session emailSession = Session.getDefaultInstance(properties);
            emailSession.setDebug(true);
            POP3Store emailStore = (POP3Store) emailSession.getStore(storeType);
            emailStore.connect(user, password);

            Folder emailFolder = emailStore.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
            Message[] messages = emailFolder.getMessages();
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                result += "\n" + message.getSubject();
                System.out.println("==============================");
                System.out.println("Email #" + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
            }

            emailFolder.close(false);
            emailStore.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return result;
    }

}
