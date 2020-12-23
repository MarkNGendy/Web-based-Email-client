package mailserver.backendmailclient.controllers;

import mailserver.backendmailclient.classes.Contact;


public class ContactBody {

    private Contact contact;
    private String user;
    private int ind;
    String editingMail;

    public ContactBody(String user,Contact contact, int ind, String editingMail){
        this.contact = contact;
        this.user = user;
        this.ind = ind;
        this.editingMail = editingMail;
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
}
