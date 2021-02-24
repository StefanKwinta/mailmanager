package com.springprojects.mailmanager.serialization;

public class MailContent {
    private String subject;
    private String getFrom;
    private String content;

    public MailContent(String subject, String getFrom, String content) {
        this.subject = subject;
        this.getFrom = getFrom;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGetFrom() {
        return getFrom;
    }

    public void setGetFrom(String getFrom) {
        this.getFrom = getFrom;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
