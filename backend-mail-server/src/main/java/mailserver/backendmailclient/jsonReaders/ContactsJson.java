package mailserver.backendmailclient.jsonReaders;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import mailserver.backendmailclient.classes.Contact;

public class ContactsJson extends Json {
    private List<Contact> list;

    public ContactsJson() {
    }

    public ContactsJson(List<Contact> l) {
        list = l;
    }

    @Override
    public List<Contact> readJson(String path) {
        String input = toStringJson(path);
        ContactsJson temp = new Gson().fromJson(input, ContactsJson.class);
        this.list = (temp == null) ? new ArrayList<>() : temp.list;
        return this.list;
    }
}
