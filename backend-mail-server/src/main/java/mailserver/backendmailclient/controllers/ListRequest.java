package mailserver.backendmailclient.controllers;

import mailserver.backendmailclient.Classes.User;

public class ListRequest {

    private String listname;
    private String user;

    public ListRequest(String list, String user) {
        this.listname = list;
        this.user = user;
    }

    public String getListname() {
        return listname;
    }

    public String getuser() {
        return user;
    }
}
