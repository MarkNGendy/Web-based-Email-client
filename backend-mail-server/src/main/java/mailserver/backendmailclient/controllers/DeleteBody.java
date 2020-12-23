package mailserver.backendmailclient.controllers;

import mailserver.backendmailclient.classes.Mail;
import mailserver.backendmailclient.classes.User;

import java.util.List;

public class DeleteBody {
    private List<Mail> mails;
    private String source;

    public DeleteBody(List<Mail> mails, String source) {
        this.mails = mails;
        this.source = source;
    }

    public List<Mail> getMails() {
        return mails;
    }


    public String getSource() {
        return source;
    }

}
