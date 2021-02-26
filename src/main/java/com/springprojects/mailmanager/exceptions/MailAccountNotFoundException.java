package com.springprojects.mailmanager.exceptions;

public class MailAccountNotFoundException extends RuntimeException {

    public MailAccountNotFoundException(String login) {
        super("Could not find mail account " + login);
    }
}