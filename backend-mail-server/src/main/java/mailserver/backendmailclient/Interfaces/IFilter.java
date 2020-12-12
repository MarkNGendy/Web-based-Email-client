package mailserver.backendmailclient.Interfaces;

import java.util.List;

public interface IFilter {
    public List<IMail> meetCriteria(List<IMail> mails);
}
