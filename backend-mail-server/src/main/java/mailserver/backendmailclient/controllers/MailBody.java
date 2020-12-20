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
    private List<String> receiver;

    public MailBody(String sender, List<String> receiver, String subject, String body, List<File> attachments) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.body = body;
        this.attachments = attachments;
    }

    public Mail toMail() {
        Mail ans = new Mail(sender, receiver, subject, body, attachments);
        Long time = System.currentTimeMillis();
        String mailname = Long.toString(time);
        ans.setID(mailname);
        ans.setDate(new Date(time));
        return ans;
    }
}
