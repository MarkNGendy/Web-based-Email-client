package mailserver.backendmailclient.controllers;


import mailserver.backendmailclient.Classes.Contact;
import mailserver.backendmailclient.Classes.Mail;
import mailserver.backendmailclient.Classes.MailBuilder;
import mailserver.backendmailclient.Classes.User;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class HomeController {

    @PostMapping("/mails/")
    public java.util.List<Mail> getUserMails(@RequestBody listBody listbody){
        User currentUser = listbody.user;
        List currentList = listbody.list;
        switch (currentList){
            case SENT -> {
                return currentUser.getSent();
            }
            case INBOX -> {
                return currentUser.getInbox();
            }
            case DRAFT -> {
                return currentUser.getDraft();
            }
            case TRASH -> {
                return currentUser.getTrash();
            }
            default -> {
                return null;
            }

        }
    }
    @PostMapping("/compose/")
    public Mail composedMail(@RequestBody mailBody mailbody){

        MailBuilder mailBuilder = new MailBuilder();
        mailBuilder.buildSender(mailbody.sender);
        mailBuilder.buildReciever(mailbody.reciever);
        mailBuilder.buildSubject(mailbody.subject);
        mailBuilder.buildBody(mailbody.body);
        mailBuilder.buildDate(mailbody.date);
        mailBuilder.buildAttachments(mailbody.attachments);

        Mail mail = mailBuilder.getMail();

        return mail;




    }

}
