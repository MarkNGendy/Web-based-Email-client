package mailserver.backendmailclient.Interfaces;

import java.util.List;

public interface IUser {
    public boolean signin(String email, String password);

    public boolean signup(String email, String password, String username);

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
