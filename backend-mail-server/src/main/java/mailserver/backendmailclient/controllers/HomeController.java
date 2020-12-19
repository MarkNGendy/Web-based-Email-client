package mailserver.backendmailclient.controllers;

import mailserver.backendmailclient.classes.*;
import mailserver.backendmailclient.jsonReaders.*;

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
        return readlist.getList();
    }

    @PostMapping("/compose/")
    public Mail composedMail(@RequestBody MailBody mailbody) {
        return null;
    }

    @PostMapping("/signin/")
    public Answer signin(@RequestBody DemoUsers user){
        User user1 = new User();
        return user1.signin(user);
    }

    @PostMapping("/signup/")
    public Answer signup(@RequestBody DemoUsers user){
        User user1 = new User();
        return user1.signup(user);
    }

    @PostMapping("/filter/")
    public List<Mail> filter(@RequestBody FilterSortBody filterBody){
        Filter myFilter = new Filter();
        String field = filterBody.getField();
        if (field.equalsIgnoreCase("SUBJECT")){
            return myFilter.subjectFilter(filterBody.getList(),filterBody.getCriteria());
        }
        else if (field.equalsIgnoreCase("SENDER")){
            return myFilter.senderFilter(filterBody.getList(),filterBody.getCriteria());
        }
        else {
            return filterBody.getList();
        }
    }
}
