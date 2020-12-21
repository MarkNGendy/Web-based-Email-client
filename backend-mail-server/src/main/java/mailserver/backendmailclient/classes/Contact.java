package mailserver.backendmailclient.classes;

import mailserver.backendmailclient.interfaces.IContact;

import java.util.*;

public class Contact implements IContact {
    private String userName;
    private List<String> mails;

    public Contact(String userName, List<String> mails) {
        this.userName = userName;
        this.mails = mails;
    }
    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) { this.userName = userName; }

    @Override
    public void setMails(List<String> mails) { this.mails = mails; }

    @Override
    public List<String> getMails() { return mails; }

    @Override
    public void removeMail(int index) { mails.remove(index); }

    @Override
    public void addMail(String email) { mails.add(email); }

}
