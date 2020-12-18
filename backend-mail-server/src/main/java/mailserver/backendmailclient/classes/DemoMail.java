package mailserver.backendmailclient.classes;

import java.util.*;

public class DemoMail {
    protected String id;
    protected String srcFolder;
    protected String subject;
    protected Date date;
    protected String sender;
    protected List<String> reciever;

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public String getSrcFolder() {
        return srcFolder;
    }

    public void setSrcFolder(String srcFolder) {
        this.srcFolder = srcFolder;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getRecievers() {
        return reciever;
    }

    public void setReciever(List<String> reciever) {
        this.reciever = reciever;
    }

}
