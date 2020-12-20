package mailserver.backendmailclient.controllers;

import mailserver.backendmailclient.classes.Mail;

import java.util.List;

public class ReadMailBody {

    private List<Mail> list;
    private String ID;


    public ReadMailBody(List<Mail> list, String ID) {
        this.list = list;
        this.ID = ID;
    }

    public List<Mail> getList() { return list; }

    public String getID() {return  ID;}

}
