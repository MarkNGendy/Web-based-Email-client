package mailserver.backendmailclient.Classes;

import java.io.File;
import java.util.List;

public class MailEngineer {
    private MailBuilder mailBuilder;
    public MailEngineer(MailBuilder mailBuilder){
        this.mailBuilder = mailBuilder;
    }
    public Mail getmail(){
        return this.mailBuilder.getMail();
    }
    public void makeMail(String subject, int date , String body , List<File> attachment , Contact sender , Contact reciever){
        this.mailBuilder.buildSubject(subject);
        this.mailBuilder.buildDate(date);
        this.mailBuilder.buildAttachments(attachment);
        this.mailBuilder.buildSender(sender);
        this.mailBuilder.buildReciever(reciever);

    }
    
}
