package mailserver.backendmailclient.Interfaces;

import java.util.List;

import mailserver.backendmailclient.Classes.Answer;
import mailserver.backendmailclient.Classes.DemoUsers;

public interface IUser {
    public Answer signin(DemoUsers input);

    public Answer signup(DemoUsers input);

    public boolean setFolderList(List<IFolder> folders);

    public List<IFolder> getFolderList();

    public boolean addFolder(String name);

    public boolean removeFolder(int index);

    public boolean setContacts(List<IContact> contacts);

    public List<IContact> getContacts();

    public boolean addContact(IContact contact);

    public boolean removeContact(int index);

    public List<IMail> listmails(String folder);

    public List<IMail> sort(String criteria);

}
