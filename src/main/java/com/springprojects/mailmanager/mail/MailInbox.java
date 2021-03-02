package com.springprojects.mailmanager.mail;

import com.springprojects.mailmanager.model.MailAccount;
import com.springprojects.mailmanager.network.IMAPMailStoreConnectionEstablisher;
import com.springprojects.mailmanager.network.MailStoreConnectionEstablisher;
import com.springprojects.mailmanager.network.POP3MailStoreConnectionEstablisher;
import com.springprojects.mailmanager.model.MailContent;
import com.springprojects.mailmanager.utilities.MessageTestExtractor;
import com.sun.mail.imap.IMAPStore;
import com.sun.mail.pop3.POP3Store;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MailInbox {
    public static List<MailContent> getInbox(String login, String password){
        ArrayList<MailContent> mails = new ArrayList<>();
        try {
            MailStoreConnectionEstablisher mailStoreConnectionEstablisher = new IMAPMailStoreConnectionEstablisher();
            IMAPStore emailStore = (IMAPStore) mailStoreConnectionEstablisher.getStore(login,password);

            Folder emailFolder = emailStore.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
            Message[] messages = emailFolder.getMessages();

            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                mails.add(new MailContent(message.getSubject(),message.getFrom()[0].toString(),MessageTestExtractor.getTextFromMessage(message),message.getReceivedDate()));
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

    public static List<MailContent> getInbox(List<MailAccount> mailAccountList){
        ArrayList<MailContent> mails = new ArrayList<>();
        try {
            MailStoreConnectionEstablisher mailStoreConnectionEstablisher = new IMAPMailStoreConnectionEstablisher();
            IMAPStore emailStore = (IMAPStore) mailStoreConnectionEstablisher.getStore(mailAccountList.get(0).getLogin(),mailAccountList.get(0).getPassword());

            Folder emailFolder = emailStore.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
            Message[] messages = emailFolder.getMessages();

            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                mails.add(new MailContent(message.getSubject(),message.getFrom()[0].toString(),MessageTestExtractor.getTextFromMessage(message),message.getReceivedDate()));
            }
            emailFolder.close(false);
            emailStore.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(mails, new Comparator<MailContent>() {
            public int compare(MailContent m1, MailContent m2) {
                if (m1.getReceiveDate() == null || m2.getReceiveDate() == null)
                    return 0;
                return m1.getReceiveDate().compareTo(m2.getReceiveDate());
            }
        });
        return mails;
    }



}
