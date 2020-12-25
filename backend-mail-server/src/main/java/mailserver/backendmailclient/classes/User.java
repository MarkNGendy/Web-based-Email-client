package mailserver.backendmailclient.classes;

import java.util.*;

import mailserver.backendmailclient.interfaces.*;
import mailserver.backendmailclient.jsonReaders.*;

public class User extends DemoUsers implements IUser {

    private List<DemoMail> mails;
    private List<Contact> friends;
    private List<IFolder> folders;
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
    public boolean setFolderList(List<IFolder> folders) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<IFolder> getFolderList() {
        // TODO Auto-generated method stub
        return null;
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
    public Answer addContact(Contact contact, String user) {
        Contact c = new Contact();
        Sort s = new Sort();
        friends = c.readContacts(user);
        for(Contact con: friends){
            if(con.getUserName().equalsIgnoreCase(contact.getUserName()))
                return new Answer(false,"the contact name is already exist !");
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
        for(Contact con: RContacts){
            for(Contact con2: friends){
                if(con.getUserName().equalsIgnoreCase(con2.getUserName())){
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
        for(Contact con : friends){
            if(con.getId()==ID){
                con.setMails(mails);
                con.setUserName(username);
                break;
            }
        }
        c.writeContacts(friends, user);
        return friends;
    }


}
