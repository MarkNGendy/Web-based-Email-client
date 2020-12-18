package mailserver.backendmailclient.interfaces;

import java.util.List;

public interface IFilter {
    public List<IMail> meetCriteria(List<IMail> mails);
}
