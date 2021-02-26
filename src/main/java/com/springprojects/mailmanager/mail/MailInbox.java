package com.springprojects.mailmanager.mail;

import com.springprojects.mailmanager.network.MailStoreConnectionEstablisher;
import com.springprojects.mailmanager.network.POP3MailStoreConnectionEstablisher;
import com.springprojects.mailmanager.model.MailContent;
import com.sun.mail.pop3.POP3Store;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MailInbox {
    public static List<MailContent> getInbox(String login, String password){
        ArrayList<MailContent> mails = new ArrayList<>();
        try {
            MailStoreConnectionEstablisher mailStoreConnectionEstablisher = new POP3MailStoreConnectionEstablisher();
            POP3Store emailStore = (POP3Store) mailStoreConnectionEstablisher.getStore(login,password);

            Folder emailFolder = emailStore.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
            Message[] messages = emailFolder.getMessages();

            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                mails.add(new MailContent(message.getSubject(),message.getFrom()[0].toString(),getTextFromMessage(message)));
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

    private static String getTextFromMessage(Message message) throws MessagingException, IOException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart)  throws MessagingException, IOException{
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
            } else if (bodyPart.getContent() instanceof MimeMultipart){
                result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
            }
        }
        return result;
    }


}
