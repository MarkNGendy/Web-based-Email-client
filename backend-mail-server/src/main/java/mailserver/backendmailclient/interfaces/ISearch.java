package mailserver.backendmailclient.interfaces;

import mailserver.backendmailclient.classes.Contact;
import mailserver.backendmailclient.classes.Mail;

import java.util.List;

public interface ISearch {

    public List<Mail> subjectSearch(List<Mail> list, String search);

    public List<Mail> bodySearch(List<Mail> list, String search);

    public List<Mail> senderSearch(List<Mail> list, String search);

    public List<Mail> receiversSearch(List<Mail> list, String search);

    public List<Mail> wholeSearch(List<Mail> list, String search);

    public List<Contact> contactsSearch(List<Contact> contacts, String search);


}
