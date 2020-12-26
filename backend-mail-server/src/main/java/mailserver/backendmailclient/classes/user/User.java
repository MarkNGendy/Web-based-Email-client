package mailserver.backendmailclient.classes.user;

import java.io.File;
import java.io.IOException;
import java.util.*;

import mailserver.backendmailclient.classes.*;
import mailserver.backendmailclient.classes.manipulating.*;
import mailserver.backendmailclient.classes.folder.*;
import mailserver.backendmailclient.classes.mail.*;
import mailserver.backendmailclient.interfaces.*;
import mailserver.backendmailclient.jsonReaders.*;

public class User extends DemoUsers implements IUser {

    private List<DemoMail> mails;
    private List<Contact> friends;
    private String wrong = "Something wrong!";
    private String usersPath = "Server/Users.json";

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
    public List<String> getFolderList(String user) {
        File userfolders = new File("Server/" + user + "/folders/UserFolders");
        String[] ans = userfolders.list();
        List<String> t = (ans == null) ? new ArrayList<>() : Arrays.asList(ans);
        return t;
    }

    @Override
    public Answer signin(DemoUsers input) {

        MailServer server = MailServer.getInstance();
        if (!server.buildServe())
            return new Answer(false, wrong);

        JsonFactory factory = new JsonFactory();
        Json readlist = factory.jsfactory(ReaderType.USERS, null);
        List<DemoUsers> userslist = (List<DemoUsers>) readlist.readJson(usersPath);

        for (DemoUsers demoUsers : userslist) {
            if (input.getemail().equalsIgnoreCase(demoUsers.getemail())
                    && input.getPassword().equals(demoUsers.getPassword())) {
                return new Answer(true, demoUsers.getUsername());
            }
        }
        return new Answer(false, "Email or password is wrong!");

    }

    @Override
    public Answer signup(DemoUsers input) {

        MailServer server = MailServer.getInstance();
        if (!server.buildServe())
            return new Answer(false, wrong);

        JsonFactory factory = new JsonFactory();
        Json readlist = factory.jsfactory(ReaderType.USERS, null);
        List<DemoUsers> userslist = (List<DemoUsers>) readlist.readJson(usersPath);
        for (DemoUsers demoUsers : userslist) {
            if (input.getemail().equalsIgnoreCase(demoUsers.getemail())) {
                return new Answer(false, "This email already exists!");
            }
        }

        userslist.add(input);
        readlist = factory.jsfactory(ReaderType.USERS, userslist);
        readlist.writeJson(readlist, usersPath);

        UserBuilder builder = new UserBuilder(input);
        if (builder.newUserBuilder())
            return new Answer(true, "Signed up successfully");
        return new Answer(false, wrong);

    }

    @Override
    public boolean addFolder(String user, String name) {
        List<String> list = getFolderList(user);
        int index = list.indexOf(name);
        if (index != -1)
            return false;
        File newfolder = new File("Server/" + user + "/folders/UserFolders/" + name);
        newfolder.mkdir();
        File mails = new File(newfolder, "mails.json");
        try {
            mails.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean renameFolder(String user, String oldName, String newName) {
        List<String> list = getFolderList(user);
        int index = list.indexOf(newName);
        if (index != -1)
            return false;
        File oldFolder = new File("Server/" + user + "/folders/UserFolders/" + oldName);
        File newFolder = new File("Server/" + user + "/folders/UserFolders/" + newName);
        return oldFolder.renameTo(newFolder);
    }

    @Override
    public boolean removeFolder(String user, String name) {
        List<String> list = getFolderList(user);
        int index = list.indexOf(name);
        if (index == -1)
            return false;
        File target = new File("Server/" + user + "/folders/UserFolders/" + name);
        IFolder folder = new Folder();
        return folder.deleteFolder(target);
    }

    @Override
    public Answer addContact(Contact contact, String user) {
        Contact c = new Contact();
        Sort s = new Sort();
        friends = c.readContacts(user);
        for (Contact con : friends) {
            if (con.getUserName().equalsIgnoreCase(contact.getUserName()))
                return new Answer(false, "the contact name is already exist !");
        }
        friends.add(contact);
        s.contactsSorter(friends, "ASCENDING");
        c.writeContacts(friends, user);
        return new Answer(true, "Contact has added successfully");
    }

    @Override
    public List<Contact> removeContact(List<Contact> RContacts, String user) {
        Contact c = new Contact();
        friends = c.readContacts(user);
        for (Contact con : RContacts) {
            for (Contact con2 : friends) {
                if (con.getUserName().equalsIgnoreCase(con2.getUserName())) {
                    friends.remove(con2);
                    break;
                }
            }
        }
        c.writeContacts(friends, user);
        return friends;
    }

    @Override
    public List<Contact> editContactMails(String user, Long ID, String username, List<String> mails) {
        Contact c = new Contact();
        friends = c.readContacts(user);
        for (Contact con : friends) {
            if (con.getId() == ID) {
                con.setMails(mails);
                con.setUserName(username);
                break;
            }
        }
        c.writeContacts(friends, user);
        return friends;
    }

}
