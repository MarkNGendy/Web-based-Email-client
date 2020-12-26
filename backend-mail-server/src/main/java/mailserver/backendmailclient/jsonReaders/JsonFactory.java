package mailserver.backendmailclient.jsonReaders;

import java.util.List;

import mailserver.backendmailclient.classes.*;
import mailserver.backendmailclient.classes.mail.*;
import mailserver.backendmailclient.classes.user.*;

public class JsonFactory {

    public Json jsfactory(ReaderType t, Object list) {
        switch (t) {
            case MAIL:
                return new MailJson();
            case CONTACTS:
                return new ContactsJson((List<Contact>) list);
            case MAILSLIST:
                return new MailsListJson((List<DemoMail>) list);
            case TRASH:
                return new TrashJson((List<Trash>) list);
            case USERS:
                return new UsersJson((List<DemoUsers>) list);
        }
        return null;
    }
}