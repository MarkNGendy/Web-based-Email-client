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

    public void addContact(Contact contact);

    public void removeContact(int index);

    public void editContactMails(int contactInd, int operation, int removedMailInd, String newMail);

    public List<IMail> listmails(String folder);

}
