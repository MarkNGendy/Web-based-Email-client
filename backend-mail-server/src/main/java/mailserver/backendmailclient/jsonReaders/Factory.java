package mailserver.backendmailclient.jsonReaders;

public class Factory {
    public ReaderList factory(String s) {
        if (s.equals("mail"))
            return new MailsJson();
        if (s.equals("trash"))
            return new TrashJson();
        if (s.equals("user"))
            return new UsersJson();
        if (s.equals("contact"))
            return new ContactsJson();
        return null;
    }
}
