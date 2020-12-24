package mailserver.backendmailclient.controllers;

import com.google.gson.Gson;
import mailserver.backendmailclient.classes.*;
import mailserver.backendmailclient.jsonReaders.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class HomeController {


    @PostMapping("/mails/")
    public List<Mail> getUserMails(@RequestBody ListRequest listRequest) {
        File file = new File(
                "Server/" + listRequest.getuser() + "/folders/" + listRequest.getListname() + "/mails.json");
        MailsListJson reader = new MailsListJson();
        List<DemoMail> temp = reader.readJson(file.getPath());
        return reader.readMailsFromFolders(temp, listRequest);
    }

    @PostMapping("/saveDraft/")
    public Answer saveDraft(@RequestBody MailBody mailBody) throws IOException {
        Mail mail = new Mail();
        return mail.saveDraft(mailBody);
    }

    @PostMapping(value = "/compose/", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    public Answer composedMail(@RequestPart("mails") String mails, @RequestPart("files") List<MultipartFile> files) throws IOException {
        Gson gson = new Gson();
        MailBody mailBody = gson.fromJson(mails, MailBody.class);
        mailBody.setAttachments(files);
        System.out.println(files.get(0).getOriginalFilename());
        Mail mail = new Mail();
        return mail.sendMail(mailBody);
    }

    @PostMapping("/signin/")
    public Answer signin(@RequestBody DemoUsers user) {
        User user1 = new User();
        return user1.signin(user);
    }

    @PostMapping("/signup/")
    public Answer signup(@RequestBody DemoUsers user) {
        User user1 = new User();
        return user1.signup(user);
    }

    @PostMapping("/read/users/")
    public List<DemoUsers> readUsers(DemoUsers user) {
        JsonFactory factory = new JsonFactory();
        Json readlist = factory.jsfactory(ReaderType.USERS, null);
        List<DemoUsers> userslist = (List<DemoUsers>) readlist.readJson("Server/Users.json");
        List<DemoUsers> retList = new ArrayList<>();
        for (int i = 0; i < userslist.size(); i++) {
            if (user.getemail().equalsIgnoreCase(userslist.get(i).getemail())) {
                continue;
            }
            retList.add(userslist.get(i));
        }
        return userslist;
    }

    @PostMapping("/read/")
    public Mail read(@RequestBody ReadMailBody ReadBody) {
        for (Mail m : (ReadBody.getList())) {
            if (ReadBody.getID().equalsIgnoreCase(m.getID())) {
                return m;
            }
        }
        return ReadBody.getList().get(0);
    }

    @PostMapping("/filter/")
    public List<Mail> filter(@RequestBody FSSBody filterBody) {
        Filter myFilter = new Filter();
        String field = filterBody.getField();
        if (field.equalsIgnoreCase("SUBJECT")) {
            return myFilter.subjectFilter(filterBody.getList(), filterBody.getCriteria());
        } else if (field.equalsIgnoreCase("SENDER")) {
            return myFilter.senderFilter(filterBody.getList(), filterBody.getCriteria());
        } else {
            return filterBody.getList();
        }
    }

    @PostMapping("/sort/")
    public List<Mail> sort(@RequestBody FSSBody sortBody) {
        Sort mySorter = new Sort();
        String field = sortBody.getField();
        if (field.equalsIgnoreCase("SUBJECT")) {
            return mySorter.subjectSorter(sortBody.getList(), sortBody.getCriteria());
        } else if (field.equalsIgnoreCase("SENDER")) {
            return mySorter.senderSorter(sortBody.getList(), sortBody.getCriteria());
        } else if (field.equalsIgnoreCase("RECEIVERS")) {
            return mySorter.receiversSorter(sortBody.getList(), sortBody.getCriteria());
        } else if (field.equalsIgnoreCase("IMPORTANCE")) {
            return mySorter.importanceSorter(sortBody.getList(), sortBody.getCriteria());
        } else if (field.equalsIgnoreCase("TIME")) {
            return mySorter.dateSorter(sortBody.getList(), sortBody.getCriteria());
        } else if (field.equalsIgnoreCase("BODY")) {
            return mySorter.bodySorter(sortBody.getList(), sortBody.getCriteria());
        } else if (field.equalsIgnoreCase("ATTACHMENTS")) {
            return mySorter.attachmentSorter(sortBody.getList(), sortBody.getCriteria());
        } else {
            return sortBody.getList();
        }
    }

    @PostMapping("/deleteMails/")
    public void deleteMails(@RequestBody DeleteBody deleteBody) {
        Mail mail = new Mail();
        for (Mail m : deleteBody.getMails()) {
            String fullPath = "Server/" + deleteBody.getUserEmail() + "/folders/" + deleteBody.getSource();
            mail.sendToTrash(fullPath, m.getID());
        }
    }

    @PostMapping("/search/")
    public List<Mail> search(@RequestBody FSSBody searchBody) {
        Search mySearcher = new Search();
        String field = searchBody.getField();
        if (field.equalsIgnoreCase("SUBJECT")) {
            return mySearcher.subjectSearch(searchBody.getList(), searchBody.getCriteria());
        } else if (field.equalsIgnoreCase("BODY")) {
            return mySearcher.bodySearch(searchBody.getList(), searchBody.getCriteria());
        } else if (field.equalsIgnoreCase("SENDER")) {
            return mySearcher.senderSearch(searchBody.getList(), searchBody.getCriteria());
        } else if (field.equalsIgnoreCase("RECEIVERS")) {
            return mySearcher.receiversSearch(searchBody.getList(), searchBody.getCriteria());
        } else if (field.equalsIgnoreCase("WHOLE")) {
            return mySearcher.wholeSearch(searchBody.getList(), searchBody.getCriteria());
        } else {
            return searchBody.getList();
        }

    }

    @PostMapping("/contact/")
    public List<Contact> ViewContact(@RequestBody DemoUsers user) {
        Contact c = new Contact();
        return c.readContacts(user.getemail());
    }

    @PostMapping("/addContact/")
    public Answer addContact(@RequestBody ContactBody contactBody) {
        User u = new User();
        Contact c = new Contact(contactBody.getUserName(), contactBody.getMails());
        return u.addContact(c, contactBody.getUser());
    }

    @PostMapping("/removeContact/")
    public List<Contact> removeContact(@RequestBody ContactBody contactBody) {
        User u = new User();
        return u.removeContact(contactBody.getRContacts(), contactBody.getUser());
    }

    @PostMapping("/editContact/")
    public List<Contact> removeMail(@RequestBody ContactBody contactBody) {
        User u = new User();
        Contact c = new Contact(contactBody.getUserName(), contactBody.getMails());
        return u.editContactMails(contactBody.getUser(), contactBody.getInd(), c);
    }


}
