package mailserver.backendmailclient.controllers;

public class LFnameBody {

    private String listname;
    private String newName;
    private String user;

    public LFnameBody(String listname, String user, String newName) {
        this.listname = listname;
        this.user = user;
        this.newName = newName;
    }

    public String getListname() {
        return listname;
    }

    public String getUser() {
        return user;
    }

    public String getNewName() {
        return newName;
    }
}
