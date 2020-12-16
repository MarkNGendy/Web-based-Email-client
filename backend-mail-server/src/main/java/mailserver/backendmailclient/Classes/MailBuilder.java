package mailserver.backendmailclient.Classes;

import java.io.File;
import java.util.Date;
import java.util.List;

public class MailBuilder implements mailserver.backendmailclient.Interfaces.IMailBuilder {
    private Mail mail;

    public MailBuilder() {
        this.mail = new Mail();
    }

    @Override
    public void buildSubject(String subject) {
        mail.setSubject(subject);
    }

    @Override
    public void buildDate(Date date) {
        mail.setDate(date);
    }

    @Override
    public void buildBody(String body) {
        mail.setBody(body);
    }

    @Override
    public void buildAttachments(List<File> attachment) {
        mail.setAttachments(attachment);
    }

    @Override
    public void buildSender(String sender) {
        mail.setSender(sender);
    }

    @Override
    public void buildReciever(List<String> reciever) {
        mail.setReciever(reciever);
    }

    @Override
    public Mail getMail() {
        return this.mail;
    }
}
