package com.teamone.approvalserver.Services.Email;

import java.io.File;

public class EmailDetails{


    final String host = "smtp.gmail.com";
    final String from = "v1approvalnotification@gmail.com";
    String to;

    String subject;
    String content;

    File attachment;

    public EmailDetails(String to,String subject,String content){
        this.to = to;
        this.subject = subject;
        this.content = content;
    }
    public EmailDetails(String to,String subject,String content,File attachment){
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.attachment = attachment;
    }

    public File getAttachment() {
        return attachment;
    }

    public String getHost() {
        return host;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }
}
