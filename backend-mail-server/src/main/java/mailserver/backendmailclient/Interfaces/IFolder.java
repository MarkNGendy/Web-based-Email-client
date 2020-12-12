package mailserver.backendmailclient.Interfaces;

public interface IFolder {
    public boolean deleteFolder(String path);

    public boolean moveFile(String dist, String source, boolean deleteSource);

    public boolean buildFolder(String name, String path);
}
