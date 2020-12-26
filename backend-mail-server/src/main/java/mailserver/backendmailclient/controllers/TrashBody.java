package mailserver.backendmailclient.controllers;

import mailserver.backendmailclient.classes.Mail;

import java.util.List;

public class TrashBody {
    private List<Mail> mails;
    private String user;
    private String currFolder;

    public TrashBody() {
    }

    public String getCurrFolder() {
        return currFolder;
    }

    public void setCurrFolder(String currFolder) {
        this.currFolder = currFolder;
    }

    public TrashBody(List<Mail> mails, String user, String currFolder) {
        this.mails = mails;
        this.user = user;
        this.currFolder = currFolder;
    }


    public List<Mail> getMails() {
        return mails;
    }

    public void setMails(List<Mail> mails) {
        this.mails = mails;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
