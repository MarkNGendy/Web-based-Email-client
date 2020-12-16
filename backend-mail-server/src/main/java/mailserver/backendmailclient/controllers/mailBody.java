package mailserver.backendmailclient.controllers;

import mailserver.backendmailclient.Classes.Contact;
import mailserver.backendmailclient.Classes.User;

import java.io.File;
import java.util.Date;
import java.util.List;

public class mailBody {
    String subject;
    Date date;
    String body;
    List<File> attachments;
    Contact sender;
    Contact reciever;
    public mailBody(String subject, Date date, String body,
                    List<File> attachments, User sender, User reciever){
        this.subject =subject;
        this.date = date;
        this.body = body;
        this.attachments = attachments;
        this.sender = sender;
        this.reciever = reciever;

    }
}
