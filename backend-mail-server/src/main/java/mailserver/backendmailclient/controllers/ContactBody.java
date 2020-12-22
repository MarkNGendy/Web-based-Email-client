package mailserver.backendmailclient.controllers;

import mailserver.backendmailclient.classes.Contact;


public class ContactBody {

    private Contact contact;
    private String user;
    private int ind;

    public ContactBody(String user,Contact contact, int ind){
        this.contact = contact;
        this.user = user;
        this.ind = ind;
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
}
