package com.springprojects.mailmanager.network;

import javax.mail.Store;

public interface MailStoreConnectionEstablisher {
    public Store getStore(String login, String password);
}
