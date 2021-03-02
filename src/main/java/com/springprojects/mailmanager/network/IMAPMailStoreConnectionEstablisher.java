package com.springprojects.mailmanager.network;

import com.sun.mail.imap.IMAPStore;
import com.sun.mail.pop3.POP3Store;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class IMAPMailStoreConnectionEstablisher implements MailStoreConnectionEstablisher {

    @Override
    public Store getStore(String login, String password) {
        IMAPStore emailStore = null;
        try {
            Properties properties = new Properties();
            properties.put("mail.imap.host", HostAssigner.assignIMAPHost(login));
            properties.put("mail.imap.port", "993");
            properties.put("mail.imap.ssl.enable", "true");
            properties.put("mail.protocol.ssl.trust", "true");
            MailSSLSocketFactory socketFactory= new MailSSLSocketFactory();
            socketFactory.setTrustAllHosts(true);
            properties.put("mail.imap.ssl.socketFactory", socketFactory);
            Session emailSession = Session.getDefaultInstance(properties);
            emailSession.setDebug(true);
            emailStore = (IMAPStore) emailSession.getStore("imap");
            emailStore.connect(login, password);
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
