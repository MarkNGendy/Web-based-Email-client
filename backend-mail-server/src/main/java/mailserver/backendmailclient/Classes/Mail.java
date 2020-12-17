package mailserver.backendmailclient.Classes;

import java.io.File;
import java.util.List;

import org.apache.tomcat.util.bcel.Const;

import mailserver.backendmailclient.Interfaces.IFolder;
import mailserver.backendmailclient.controllers.MailBody;

public class Mail extends DemoMail {
    private String body;
    private List<File> attachments;

    public Mail() {
    }

    public Mail(String sender, List<String> reciever, String subject, String body, List<File> attachments) {
        this.sender = sender;
        this.reciever = reciever;
        this.subject = subject;
        this.body = body;
        this.attachments = attachments;
    }

    public List<File> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<File> attachments) {
        this.attachments = attachments;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private String wrong = "Something wrong!";

    public Answer saveDraft(MailBody mailBody) {
        Mail mail = mailBody.toMail();
        mail.setSrcFolder("Draft");
        BuilderMail builderMail = new BuilderMail();
        if (builderMail.buildMail(mail)) {
            return new Answer(true, "Draft saved successfully.");
        }
        return new Answer(false, wrong);
    }

    public Answer sendMail(MailBody mailBody) {
        Mail mail = mailBody.toMail();
        mail.setSrcFolder("Sent");
        BuilderMail builderMail = new BuilderMail();
        if (!builderMail.buildMail(mail)) {
            return new Answer(false, wrong);
        }
        IFolder folder = new Folder();
        File source = new File("server/" + mail.getSender() + "/folders/Sent/" + mail.getID());
        mail.setSrcFolder("Inbox");
        for (String reciever : mail.getRecievers()) {
            File inbox = new File("server/" + reciever + "/folders/Inbox");
            File dest = new File(inbox, mail.getID());
            if (!folder.copyFolder(dest.getPath(), source.getPath()))
                return new Answer(false, wrong);
            DemoMail demoMail = mail;
            builderMail.addToMailsFile(inbox, demoMail);
        }
        return new Answer(true, "Mail sent successfully.");
    }
}
