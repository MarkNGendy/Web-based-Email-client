package mailserver.backendmailclient.controllers.requestdata;

import mailserver.backendmailclient.classes.Contact;

import java.util.List;

public class SSContactBody {
    private List<Contact> contacts;
    String criteria;

    public SSContactBody(List<Contact> contacts, String criteria) {
        this.contacts = contacts;
        this.criteria = criteria;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }
}
