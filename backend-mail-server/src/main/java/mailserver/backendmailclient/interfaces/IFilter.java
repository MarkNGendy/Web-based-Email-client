package mailserver.backendmailclient.interfaces;

import mailserver.backendmailclient.classes.Mail;

import java.util.List;

public interface IFilter {
    public List<Mail> subjectFilter(List<Mail> mails, String criteria);

    public List<Mail> senderFilter(List<Mail> mails, String criteria);

}
