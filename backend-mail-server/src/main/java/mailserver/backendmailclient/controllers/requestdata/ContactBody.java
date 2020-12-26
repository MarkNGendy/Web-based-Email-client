package mailserver.backendmailclient.controllers.requestdata;

import mailserver.backendmailclient.classes.Contact;

import java.util.List;

public class ContactBody {
    private String userName;
    private List<String> mails;
    private List<Contact> RContacts;
    private String user;
    private long ID;

    public ContactBody(String userName, List<String> mails, List<Contact> RContacts, String user, long ID) {
        this.userName = userName;
        this.mails = mails;
        this.RContacts = RContacts;
        this.user = user;
        this.ID = ID;
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

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getID() {
        return ID;
    }

    public String getUser() {
        return user;
    }

    public List<Contact> getRContacts() {
        return RContacts;
    }
}
