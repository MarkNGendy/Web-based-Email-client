package mailserver.backendmailclient.interfaces;

import java.util.List;

import mailserver.backendmailclient.classes.Answer;
import mailserver.backendmailclient.classes.Contact;
import mailserver.backendmailclient.classes.DemoUsers;

public interface IUser {
    public Answer signin(DemoUsers input);

    public Answer signup(DemoUsers input);

    public boolean setFolderList(List<IFolder> folders);

    public List<IFolder> getFolderList();

    public boolean addFolder(String name);

    public boolean removeFolder(int index);

    public List<Contact> addContact(Contact contact, String user);

    public List<Contact> removeContact(int index,String user);

    public List<Contact> editContactMails(String user,int contactInd, int operation, String theMail);

    public List<IMail> listmails(String folder);

}
