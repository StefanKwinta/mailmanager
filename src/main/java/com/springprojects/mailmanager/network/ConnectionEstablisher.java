package com.springprojects.mailmanager.network;

import javax.mail.Store;

public interface ConnectionEstablisher {
    public Store connect(String host, String user, String password);
}
