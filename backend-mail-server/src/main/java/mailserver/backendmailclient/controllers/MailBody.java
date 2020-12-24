package mailserver.backendmailclient.controllers;

import java.io.File;
import java.util.Date;
import java.util.List;

import mailserver.backendmailclient.classes.Mail;
import org.springframework.web.multipart.MultipartFile;

public class MailBody {
    private String subject;
    private String body;
    private List<MultipartFile> attachments;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<MultipartFile> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<MultipartFile> attachments) {
        this.attachments = attachments;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    private String sender;
    private List<String> receivers;
    private int importance;

    public MailBody(String subject, String body, String sender, List<String> receivers, int importance) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.receivers = receivers;
        this.importance = importance;
    }

    public MailBody(String sender, List<String> receivers, String subject, String body, List<MultipartFile> attachments, int importance) {
        this.sender = sender;
        this.receivers = receivers;
        this.subject = subject;
        this.body = body;
        this.attachments = attachments;
        this.importance = importance;
    }

    public Mail toMail() {
        Mail ans = new Mail(sender, receivers, subject, body, attachments,importance);
        Long time = System.currentTimeMillis();
        String mailname = Long.toString(time);
        ans.setID(mailname);
        ans.setDate(new Date(time));
        return ans;
    }
}
