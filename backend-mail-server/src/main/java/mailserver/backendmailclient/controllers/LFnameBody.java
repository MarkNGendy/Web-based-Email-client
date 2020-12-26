package mailserver.backendmailclient.controllers;

public class LFnameBody {

    private String name;
    private String newName;
    private String user;

    public LFnameBody(String name, String user, String newName) {
        this.name = name;
        this.user = user;
        this.newName = newName;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getNewName() {
        return newName;
    }
}
