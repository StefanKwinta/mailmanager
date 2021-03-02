package com.springprojects.mailmanager.model;

import java.util.Date;

public class MailContent {
    private String subject;
    private String from;
    private String content;
    private Date receiveDate;

    public MailContent(String subject, String from, String content, Date receiveDate) {
        this.subject = subject;
        this.from = from;
        this.content = content;
        this.receiveDate = receiveDate;
    }


    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
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
