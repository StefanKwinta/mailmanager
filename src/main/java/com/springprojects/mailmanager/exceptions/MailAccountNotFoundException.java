package com.springprojects.mailmanager.exceptions;

public class MailAccountNotFoundException extends RuntimeException {

    public MailAccountNotFoundException(Long id) {
        super("Could not find mail account " + id);
    }
}