package mailserver.backendmailclient.jsonReaders;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import mailserver.backendmailclient.classes.DemoMail;
import mailserver.backendmailclient.classes.Mail;
import mailserver.backendmailclient.controllers.ListRequest;

public class MailsListJson extends Json {

    private List<DemoMail> list;

    public MailsListJson() {
    }

    public MailsListJson(List<DemoMail> l) {
        list = l;
    }

    public List<Mail> readMailsFromFolders(List<DemoMail> mailsHeaders, ListRequest listRequest) {
        List<Mail> retArray = new ArrayList<>();
        for (int i = 0; i < mailsHeaders.size(); i++) {
            DemoMail header = mailsHeaders.get(i);
            String filepath = "Server/" + listRequest.getuser() + "/folders/" + listRequest.getListname() + "/"
                    + header.getID() + "/mailfile.json";
            String input = toStringJson(filepath);
            Mail temp = new Gson().fromJson(input, Mail.class);
            retArray.add(temp);
        }
        return retArray;
    }

    @Override
    public List<DemoMail> readJson(String path) {
        String input = toStringJson(path);
        MailsListJson temp = new Gson().fromJson(input, MailsListJson.class);
        this.list = (temp == null) ? new ArrayList<>() : temp.list;
        return this.list;
    }
}
