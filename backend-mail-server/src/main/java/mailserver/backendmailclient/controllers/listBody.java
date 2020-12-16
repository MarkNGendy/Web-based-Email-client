package mailserver.backendmailclient.controllers;

import mailserver.backendmailclient.Classes.User;

public class listBody {

    List list;
    User user;

    public listBody(List list, User user){
        this.list=list;
        this.user=user;
    }
}
