package mailserver.backendmailclient.JsonReaders;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import mailserver.backendmailclient.Classes.DemoMail;
import mailserver.backendmailclient.Classes.Folder;
import mailserver.backendmailclient.Interfaces.IFolder;

public class MailsJson extends ReaderList<DemoMail> {

    public MailsJson() {
    }

    public MailsJson(List<DemoMail> l) {
        list = l;
    }

    public void toList(String path) {
        IFolder folder = new Folder();
        String input = folder.toStringJson(path);
        MailsJson temp = new Gson().fromJson(input, MailsJson.class);
        this.list = (temp == null) ? new ArrayList<>() : temp.getList();
    }
}
