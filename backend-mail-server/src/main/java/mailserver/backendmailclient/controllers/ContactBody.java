package mailserver.backendmailclient.controllers;

import mailserver.backendmailclient.classes.Contact;

import java.util.List;


public class ContactBody {
    private String userName;
    private List<String> mails;
    private List<Contact> RContacts;
    private String user;
    private int ind;
    private String editingMail;


    public ContactBody(String userName, List<String> mails, List<Contact> RContacts, String user, int ind, String editingMail) {
        this.userName = userName;
        this.mails = mails;
        this.RContacts = RContacts;
        this.user = user;
        this.ind = ind;
        this.editingMail = editingMail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getMails() {
        return mails;
    }

    public void setMails(List<String> mails) {
        this.mails = mails;
    }

    public void setRContacts(List<Contact> RContacts) {
        this.RContacts = RContacts;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setInd(int ind) {
        this.ind = ind;
    }

    public void setEditingMail(String editingMail) {
        this.editingMail = editingMail;
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
