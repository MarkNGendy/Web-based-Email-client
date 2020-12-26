package mailserver.backendmailclient.controllers.requestdata;

import mailserver.backendmailclient.classes.mail.*;

import java.util.List;

public class MoveBody {
    private List<Mail> mails;
    private String user;
    private String source;
    private String target;

    public MoveBody(List<Mail> mails, String user, String source, String target) {
        this.mails = mails;
        this.user = user;
        this.source = source;
        this.target = target;
    }

    public String getUser() {
        return user;
    }

    public List<Mail> getMails() {
        return mails;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
