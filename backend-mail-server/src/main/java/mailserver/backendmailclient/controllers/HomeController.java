package mailserver.backendmailclient.controllers;

import mailserver.backendmailclient.Classes.Contact;
import mailserver.backendmailclient.Classes.DemoMail;
import mailserver.backendmailclient.Classes.Mail;
import mailserver.backendmailclient.Classes.*;
import mailserver.backendmailclient.JsonReaders.*;

import java.io.File;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class HomeController {

    @PostMapping("/mails/")
    public List<DemoMail> getUserMails(@RequestBody ListRequest listRequest) {
        File file = new File(
                "server/" + listRequest.getuser() + "/folders/" + listRequest.getListname() + "/mails.json");
        ReaderList<DemoMail> readlist = new MailsJson();
        readlist.toList(file.getPath());
        List<DemoMail> mails = readlist.getList();
        return mails;
    }

    @PostMapping("/compose/")
    public Mail composedMail(@RequestBody MailBody mailbody) {
        return null;
    }

}
