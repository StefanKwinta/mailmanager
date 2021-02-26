package com.springprojects.mailmanager.model;

public class MailContent {
    private String subject;
    private String from;
    private String content;

    public MailContent(String subject, String from, String content) {
        this.subject = subject;
        this.from = from;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}