package com.springprojects.mailmanager.mail;

import com.springprojects.mailmanager.network.ConnectionEstablisher;
import com.springprojects.mailmanager.network.HostAssigner;
import com.springprojects.mailmanager.network.POP3ConnectionEstablisher;
import com.springprojects.mailmanager.serialization.MailContent;
import com.sun.mail.pop3.POP3Store;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MailInbox {
    public static List<MailContent> getInbox(String login, String password){
        ArrayList<MailContent> mails = new ArrayList<>();
        try {
            ConnectionEstablisher connectionEstablisher = new POP3ConnectionEstablisher();
            POP3Store emailStore = (POP3Store) connectionEstablisher.connect(HostAssigner.assignHost(login),login,password);

            Folder emailFolder = emailStore.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
            Message[] messages = emailFolder.getMessages();

            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                mails.add(new MailContent(message.getSubject(),message.getFrom()[0].toString(),message.getContent().toString()));
            }
            emailFolder.close(false);
            emailStore.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mails;
    }


}
