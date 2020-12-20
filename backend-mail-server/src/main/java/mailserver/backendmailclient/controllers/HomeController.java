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
        System.out.println(listRequest.getListname() + " " + listRequest.getuser());
        File file = new File(
                "Server/" + listRequest.getuser() + "/folders/" + listRequest.getListname() + "/mails.json");
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

    @PostMapping("/read/")
    public Mail read(@RequestBody ReadMailBody ReadBody){
        System.out.println(ReadBody.getList());
        for(Mail m : (ReadBody.getList())){
            if(ReadBody.getID().equalsIgnoreCase(m.getID())){
                return m;
            }
        }
        return ReadBody.getList().get(0);
    }

    @PostMapping("/filter/")
    public List<Mail> filter(@RequestBody FSSBody filterBody){
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

    @PostMapping("/sort/")
    public List<Mail> sort(@RequestBody FSSBody sortBody){
        Sort mySorter = new Sort();
        String field = sortBody.getField();
        if (field.equalsIgnoreCase("SUBJECT")){
            return mySorter.subjectSorter(sortBody.getList(),sortBody.getCriteria());
        }
        else if (field.equalsIgnoreCase("SENDER")){
            return mySorter.senderSorter(sortBody.getList(),sortBody.getCriteria());
        }
        else if (field.equalsIgnoreCase("RECEIVERS")){
            return mySorter.receiversSorter(sortBody.getList(),sortBody.getCriteria());
        }
        else if (field.equalsIgnoreCase("IMPORTANCE")){
            return mySorter.importanceSorter(sortBody.getList(),sortBody.getCriteria());
        }
        else if (field.equalsIgnoreCase("TIME")){
            return mySorter.dateSorter(sortBody.getList(),sortBody.getCriteria());
        }
        else if (field.equalsIgnoreCase("BODY")){
            return mySorter.bodySorter(sortBody.getList(),sortBody.getCriteria());
        }
        else if (field.equalsIgnoreCase("ATTACHMENTS")){
            return mySorter.attachmentSorter(sortBody.getList(),sortBody.getCriteria());
        }
        else {
            return sortBody.getList();
        }
    }

    @PostMapping("/search/")
    public List<Mail> search(@RequestBody FSSBody searchBody){
        Search mySearcher = new Search();
        String field = searchBody.getField();
        if (field.equalsIgnoreCase("SUBJECT")){
            return mySearcher.subjectSearch(searchBody.getList(),searchBody.getCriteria());
        }
        else if (field.equalsIgnoreCase("BODY")){
            return mySearcher.bodySearch(searchBody.getList(),searchBody.getCriteria());
        }
        else if (field.equalsIgnoreCase("SENDER")){
            return mySearcher.senderSearch(searchBody.getList(),searchBody.getCriteria());
        }
        else if (field.equalsIgnoreCase("RECEIVERS")){
            return mySearcher.receiversSearch(searchBody.getList(),searchBody.getCriteria());
        }
        else if (field.equalsIgnoreCase("WHOLE")){
            return mySearcher.wholeSearch(searchBody.getList(),searchBody.getCriteria());
        }
        else {
            return searchBody.getList();
        }

    }


}
