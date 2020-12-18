package mailserver.backendmailclient.classes;

import java.io.File;
import java.io.IOException;

public class MailServer {

    private static MailServer mailserver = null;

    private MailServer() {
    }

    public static MailServer getInstance() {
        if (mailserver == null) {
            mailserver = new MailServer();
        }
        return mailserver;
    }

    public boolean buildServe() {
        if (new File("Server/Users.json").exists())
            return true;
        try {
            File start = new File("Server");
            start.mkdir();
            File users = new File(start, "Users.json");
            users.createNewFile();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
