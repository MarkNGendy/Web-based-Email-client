package mailserver.backendmailclient.controllers;

import mailserver.backendmailclient.classes.Contact;

import java.util.List;


public class ContactBody {

    private Contact contact;
    private List<Contact> RContacts;
    private String user;
    private int ind;
    private String editingMail;

    public ContactBody(String user,Contact contact,List<Contact> RContacts, int ind, String editingMail){
        this.contact = contact;
        this.user = user;
        this.ind = ind;
        this.editingMail = editingMail;
        this.RContacts = RContacts;
    }

    public Contact getContact() {
        return contact;
    }

    public String getUser() {
        return user;
    }

    public int getInd() {
        return ind;
    }

    public String getEditingMail() {
        return editingMail;
    }

    public List<Contact> getRContacts() {
        return RContacts;
    }
}
