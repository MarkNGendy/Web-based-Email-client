package mailserver.backendmailclient.classes;

import mailserver.backendmailclient.interfaces.*;
import mailserver.backendmailclient.jsonReaders.*;

import java.util.*;

public class Contact implements IContact {
    private String userName;
    private List<String> mails;

    public Contact() {
    }

    public Contact(String userName, List<String> mails) {
        this.userName = userName;
        this.mails = mails;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void setMails(List<String> mails) {
        this.mails = mails;
    }

    @Override
    public List<String> getMails() {
        return mails;
    }

    @Override
    public void removeMail(int index) {
        mails.remove(index);
    }

    @Override
    public void addMail(String email) {
        mails.add(email);
    }

    public void writeContacts(List<Contact> contacts, String user) {
        JsonFactory factory = new JsonFactory();
        Json readList = factory.jsfactory(ReaderType.CONTACTS, contacts);
        readList.writeJson(readList, "Server/" + user + "/contacts.json");
    }

    public List<Contact> readContacts(String user) {
        JsonFactory factory = new JsonFactory();
        Json readlist = factory.jsfactory(ReaderType.CONTACTS, null);
        return (List<Contact>) readlist.readJson("Server/" + user + "/contacts.json");
    }

    /* Comparators for sorting the contacts by contact name */
    public static Comparator<Contact> AContactsComparator = new Comparator<Contact>() {

        public int compare(Contact c1, Contact c22) {
            String name1 = c1.getUserName().toUpperCase();
            String name2 = c22.getUserName().toUpperCase();

            // ascending order
            return name1.compareTo(name2);

            // descending order
            // return name2.compareTo(name1);
        }
    };
    public static Comparator<Contact> DContactsComparator = new Comparator<Contact>() {

        public int compare(Contact c1, Contact c22) {
            String name1 = c1.getUserName().toUpperCase();
            String name2 = c22.getUserName().toUpperCase();

            // ascending order
            // return name1.compareTo(name2);

            // descending order
            return name2.compareTo(name1);
        }
    };

}
