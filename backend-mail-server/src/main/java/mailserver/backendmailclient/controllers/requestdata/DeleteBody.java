package mailserver.backendmailclient.controllers.requestdata;

import mailserver.backendmailclient.classes.Mail;

import java.util.List;

public class DeleteBody {
    private List<Mail> mails;
    private String source;
    private String userEmail;

    public DeleteBody(List<Mail> mails, String source, String userEmail) {
        this.mails = mails;
        this.source = source;
        this.userEmail = userEmail;
    }

    public List<Mail> getMails() {
        return mails;
    }

    public String getSource() {
        return source;
    }

    public String getUserEmail() {
        return userEmail;
    }

}
