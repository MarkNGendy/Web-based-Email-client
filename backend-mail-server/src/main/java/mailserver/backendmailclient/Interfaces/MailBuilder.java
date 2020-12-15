package mailserver.backendmailclient.Interfaces;

import mailserver.backendmailclient.Classes.Contact;
import mailserver.backendmailclient.Classes.Mail;

import java.io.File;
import java.util.List;

public interface MailBuilder {
    public void buildSubject(String subject);
    public void buildDate(int date);
    public void buildBody(String body);
    public void buildAttachments (List<File> attachment);
    public void buildSender(Contact sender);
    public void buildReciever(Contact reciever);
    public Mail getMail();
}
