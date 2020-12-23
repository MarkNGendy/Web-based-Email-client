package mailserver.backendmailclient.controllers;

import java.io.File;
import java.util.Date;
import java.util.List;

import mailserver.backendmailclient.classes.Mail;

public class MailBody {
    private String subject;
    private String body;
    private List<File> attachments;
    private String sender;
    private List<String> receivers;
    private int importance;

    public MailBody(String sender, List<String> receivers, String subject, String body, List<File> attachments, int importance) {
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
