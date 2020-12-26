package mailserver.backendmailclient.interfaces;

import java.util.List;

import mailserver.backendmailclient.classes.Answer;
import mailserver.backendmailclient.classes.Contact;
import mailserver.backendmailclient.classes.user.DemoUsers;

public interface IUser {

    public Answer signin(DemoUsers input);

    public Answer signup(DemoUsers input);

    public List<String> getFolderList(String user);

    public boolean addFolder(String user, String name);

    public boolean renameFolder(String user, String oldName, String newName);

    public boolean removeFolder(String user, String name);

    public Answer addContact(Contact contact, String user);

    public List<Contact> removeContact(List<Contact> RContacts, String user);

    public List<Contact> editContactMails(String user, Long ID, String username, List<String> mails);

}
