package mailserver.backendmailclient.Interfaces;

import java.util.List;

public interface IMail {
    public boolean compose(IMail email);

    public boolean deleteMails(List<String> mailslist);

    public boolean moveMails(List<String> mailslist, String dest, String source);

}
