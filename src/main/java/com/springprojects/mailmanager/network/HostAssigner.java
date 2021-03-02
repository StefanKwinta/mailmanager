package com.springprojects.mailmanager.network;

public class HostAssigner {
    public static String assignPOP3Host(String mailAddress){
        //TODO for multipe mail domains
        return "pop." + mailAddress.substring(mailAddress.lastIndexOf("@") + 1);
    }
    public static String assignIMAPHost(String mailAddress){
        //TODO for multipe mail domains
        return "imap." + mailAddress.substring(mailAddress.lastIndexOf("@") + 1);
    }
    public static String assignSMTPHost(String mailAddress){
        //TODO for multipe mail domains
        return "smtp." + mailAddress.substring(mailAddress.lastIndexOf("@") + 1);
    }

}
