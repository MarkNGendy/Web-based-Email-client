package mailserver.backendmailclient.interfaces;

import java.util.List;

public interface IContact {
    public void setUserName(String UserName);

    public String getUserName();

    public void setMails(List<String> mails);

    public List<String> getMails();

}
