package mailserver.backendmailclient.jsonReaders;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import mailserver.backendmailclient.classes.DemoMail;
import mailserver.backendmailclient.classes.Folder;
import mailserver.backendmailclient.interfaces.IFolder;

public class MailsJson extends ReaderList<DemoMail> {

    public MailsJson() {
    }

    public MailsJson(List<DemoMail> l) {
        list = l;
    }

    public List<DemoMail> toList(String path) {
        IFolder folder = new Folder();
        String input = folder.toStringJson(path);
        MailsJson temp = new Gson().fromJson(input, MailsJson.class);
        this.list = (temp == null) ? new ArrayList<>() : temp.getList();
        return this.list;
    }
}
