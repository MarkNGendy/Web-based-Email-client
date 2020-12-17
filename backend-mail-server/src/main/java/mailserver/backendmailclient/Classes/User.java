package mailserver.backendmailclient.Classes;

import java.util.*;

import mailserver.backendmailclient.Interfaces.*;
import mailserver.backendmailclient.JsonReaders.*;

public class User extends DemoUsers implements IUser {

    private List<DemoMail> mails;
    private List<Contact> friends;
    private List<IFolder> folders;

    public List<DemoMail> getMails() {
        return mails;
    }

    public void setInbox(List<DemoMail> mails) {
        this.mails = mails;
    }

    public List<Contact> getFriends() {
        return friends;
    }

    public void setFriends(List<Contact> friends) {
        this.friends = friends;
    }

    @Override
    public boolean setFolderList(List<IFolder> folders) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<IFolder> getFolderList() {
        // TODO Auto-generated method stub
        return null;
    }

    private String wrong = "Something wrong!";
    private String usersPath = "Server/Users.json";

    @Override
    public Answer signin(DemoUsers input) {

        MailServer server = MailServer.getInstance();
        if (!server.buildServe())
            return new Answer(false, wrong);

        ReaderList<DemoUsers> readlist = new UsersJson();
        readlist.toList(usersPath);
        List<DemoUsers> userslist = readlist.getList();

        for (DemoUsers demoUsers : userslist) {
            if (input.getemail().equalsIgnoreCase(demoUsers.getemail())
                    && input.getPassword().equals(demoUsers.getPassword())) {
                return new Answer(true, "Signed in successfully");
            }
        }
        return new Answer(false, "Email or password is wrong!");

    }

    @Override
    public Answer signup(DemoUsers input) {

        MailServer server = MailServer.getInstance();
        if (!server.buildServe())
            return new Answer(false, wrong);

        IFolder folder = new Folder();
        ReaderList<DemoUsers> readlist = new UsersJson();
        readlist.toList(usersPath);
        List<DemoUsers> userslist = readlist.getList();
        for (DemoUsers demoUsers : userslist) {
            if (input.getemail().equalsIgnoreCase(demoUsers.getemail())) {
                return new Answer(false, "This email already exists!");
            }
        }

        userslist.add(input);
        readlist.setLsist(userslist);
        folder.writeJson(readlist, usersPath);

        UserBuilder builder = new UserBuilder(input);
        if (builder.newUserBuilder())
            return new Answer(true, "Signed up successfully");
        return new Answer(false, wrong);

    }

    @Override
    public boolean addFolder(String name) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeFolder(int index) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addContact(IContact contact) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeContact(int index) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<IMail> listmails(String folder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<IMail> sort(String criteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
