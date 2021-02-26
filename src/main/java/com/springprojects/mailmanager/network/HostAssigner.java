package com.springprojects.mailmanager.network;

public class HostAssigner {
    public static String assignPOP3Host(String mailAddress){
        //TODO for multipe mail domains
        return "pop.gmail.com";
    }
    public static String assignSMTPHost(String mailAddress){
        //TODO for multipe mail domains
        return "smtp.gmail.com";
    }

}
