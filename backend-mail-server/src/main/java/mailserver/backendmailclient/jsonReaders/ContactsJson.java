package mailserver.backendmailclient.jsonReaders;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import mailserver.backendmailclient.classes.Contact;
import mailserver.backendmailclient.classes.Folder;
import mailserver.backendmailclient.interfaces.IFolder;

public class ContactsJson extends ReaderList<Contact> {

    public ContactsJson() {
    }

    public ContactsJson(List<Contact> l) {
        list = l;
    }

    public List<Contact> toList(String path) {
        IFolder folder = new Folder();
        String input = folder.toStringJson(path);
        ContactsJson temp = new Gson().fromJson(input, ContactsJson.class);
        this.list = (temp == null) ? new ArrayList<>() : temp.getList();
        return this.list;
    }
}
