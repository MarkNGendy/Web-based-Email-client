package mailserver.backendmailclient.Interfaces;

public interface IFolderFactory {
    public IFilter getFilter(String name);
}
