package mailserver.backendmailclient.Classes;

import java.util.List;

import mailserver.backendmailclient.Interfaces.IContact;
import mailserver.backendmailclient.Interfaces.IFolder;
import mailserver.backendmailclient.Interfaces.IMail;
import mailserver.backendmailclient.Interfaces.IUser;

public class User implements IUser {

    private List<Mail> inbox;
    private List<Mail> sent;
    private List<Mail> draft;
    private List<Mail> trash;
    private List<Contact> friends;
    private String emailaddress;
    private String password;
    private String username;

    public List<Mail> getInbox() {
        return inbox;
    }

    public void setInbox(List<Mail> inbox) {
        this.inbox = inbox;
    }

    public List<Mail> getSent() {
        return sent;
    }

    public void setSent(List<Mail> sent) {
        this.sent = sent;
    }

    public List<Mail> getDraft() {
        return draft;
    }

    public void setDraft(List<Mail> draft) {
        this.draft = draft;
    }

    public List<Mail> getTrash() {
        return trash;
    }

    public void setTrash(List<Mail> trash) {
        this.trash = trash;
    }

    public List<Contact> getFriends() {
        return friends;
    }

    public void setFriends(List<Contact> friends) {
        this.friends = friends;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWrong() {
        return wrong;
    }

    public void setWrong(String wrong) {
        this.wrong = wrong;
    }



    private String wrong = "Something wrong!";

    @Override
    public Answer signin(DemoUsers input) {
        MailServer server = MailServer.getInstance();
        if (!server.buildServe())
            return new Answer(false, wrong);
        IFolder folder = new Folder();
        List<DemoUsers> userslist = (List<DemoUsers>) folder.readJson("/Server/Users.json");
        for (DemoUsers demoUsers : userslist) {
            if (input.getemail().equalsIgnoreCase(demoUsers.getUsername())
                    && input.getPassword().equals(demoUsers.getPassword())) {
                return new Answer(true, "Signed in successfully");
            }
        }
        return new Answer(false, "Email or password is wrong!");
    }

    @Override
    public Answer signup(DemoUsers input) {
        String path = "Server/Users.json";
        MailServer server = MailServer.getInstance();
        if (!server.buildServe())
            return new Answer(false, wrong);
        IFolder folder = new Folder();
        List<DemoUsers> userslist = (List<DemoUsers>) folder.readJson(path);
        for (DemoUsers demoUsers : userslist) {
            if (input.getemail().equalsIgnoreCase(demoUsers.getUsername())) {
                return new Answer(false, "This email already exists!");
            }
        }
        userslist.add(input);
        folder.writeJson(userslist, path);
        UserBuilder builder = new UserBuilder(input);
        if (builder.newUserBuilder())
            return new Answer(true, "Signed up successfully");
        return new Answer(false, wrong);
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
    public boolean setContacts(List<IContact> contacts) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<IContact> getContacts() {
        // TODO Auto-generated method stub
        return null;
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
