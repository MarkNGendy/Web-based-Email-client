package mailserver.backendmailclient.jsonReaders;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import mailserver.backendmailclient.classes.DemoMail;
import mailserver.backendmailclient.classes.Folder;
import mailserver.backendmailclient.classes.Mail;
import mailserver.backendmailclient.controllers.ListRequest;
import mailserver.backendmailclient.interfaces.IFolder;

public class MailsJson extends ReaderList<DemoMail> {

    public MailsJson() {
    }

    public MailsJson(List<DemoMail> l) {
        list = l;
    }

    public List<Mail> readMailsFromFolders(List<DemoMail> mailsHeaders, ListRequest listRequest) {
        List<Mail> retArray = new ArrayList<>();
        for (int i = 0; i < mailsHeaders.size(); i++) {
            IFolder folder = new Folder();
            DemoMail header = mailsHeaders.get(i);
            String filepath = "Server/" + listRequest.getuser() + "/folders/" + listRequest.getListname() + "/"
                    + header.getID() + "/mailfile.json";
            String input = folder.toStringJson(filepath);
            Mail temp = new Gson().fromJson(input, Mail.class);
            retArray.add(temp);
        }
        return retArray;
    }

    public List<DemoMail> toList(String path) {
        IFolder folder = new Folder();
        String input = folder.toStringJson(path);
        MailsJson temp = new Gson().fromJson(input, MailsJson.class);
        this.list = (temp == null) ? new ArrayList<>() : temp.getList();
        return this.list;
    }
}
