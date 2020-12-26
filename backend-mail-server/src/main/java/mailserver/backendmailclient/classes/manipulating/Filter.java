package mailserver.backendmailclient.classes.manipulating;

import mailserver.backendmailclient.classes.mail.Mail;
import mailserver.backendmailclient.interfaces.IFilter;

import java.util.ArrayList;
import java.util.List;

public class Filter implements IFilter {

    @Override
    public List<Mail> subjectFilter(List<Mail> mails, String criteria) {
        List<Mail> filteredList = new ArrayList<Mail>();
        for (Mail mail : mails) {
            if (mail.getSubject().equalsIgnoreCase(criteria)) {
                filteredList.add(mail);
            }
        }
        return filteredList;
    }

    @Override
    public List<Mail> senderFilter(List<Mail> mails, String criteria) {
        List<Mail> filteredList = new ArrayList<Mail>();
        for (Mail mail : mails) {
            if (mail.getSender().equalsIgnoreCase(criteria)) {
                filteredList.add(mail);
            }
        }
        return filteredList;
    }
}
