package mailserver.backendmailclient.interfaces;

public interface IFolderFactory {
    public IFilter getFilter(String name);
}
