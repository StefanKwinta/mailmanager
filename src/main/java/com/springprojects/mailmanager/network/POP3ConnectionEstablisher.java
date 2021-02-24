package com.springprojects.mailmanager.network;

import com.sun.mail.pop3.POP3Store;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class POP3ConnectionEstablisher implements ConnectionEstablisher{
    @Override
    public Store connect(String host, String user, String password) {
        POP3Store emailStore = null;
        try {
            Properties properties = new Properties();
            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.ssl.enable", "true");
            properties.put("mail.protocol.ssl.trust", "true");
            MailSSLSocketFactory socketFactory= new MailSSLSocketFactory();
            socketFactory.setTrustAllHosts(true);
            properties.put("mail.pop3.ssl.socketFactory", socketFactory);
            Session emailSession = Session.getDefaultInstance(properties);
            emailSession.setDebug(true);
            emailStore = (POP3Store) emailSession.getStore("pop3");
            emailStore.connect(user, password);
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return emailStore;
    }
}
