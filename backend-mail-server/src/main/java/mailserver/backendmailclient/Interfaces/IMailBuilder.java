package mailserver.backendmailclient.Interfaces;

import mailserver.backendmailclient.Classes.Contact;
import mailserver.backendmailclient.Classes.Mail;

import java.io.File;
import java.util.Date;
import java.util.List;

public interface IMailBuilder {
    public void buildSubject(String subject);

    public void buildDate(Date date);

    public void buildBody(String body);

    public void buildAttachments(List<File> attachment);

    public void buildSender(String sender);

    public void buildReciever(List<String> reciever);

    public Mail getMail();
}
