package mailserver.backendmailclient.JsonReaders;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import mailserver.backendmailclient.Classes.Contact;
import mailserver.backendmailclient.Classes.Folder;
import mailserver.backendmailclient.Interfaces.IFolder;

public class ContactsJson extends ReaderList<Contact> {

    public ContactsJson() {
    }

    public ContactsJson(List<Contact> l) {
        list = l;
    }

    public void toList(String path) {
        IFolder folder = new Folder();
        String input = folder.toStringJson(path);
        ContactsJson temp = new Gson().fromJson(input, ContactsJson.class);
        this.list = (temp == null) ? new ArrayList<>() : temp.getList();
    }
}
