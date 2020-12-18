package mailserver.backendmailclient.interfaces;

import mailserver.backendmailclient.classes.Mail;

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
