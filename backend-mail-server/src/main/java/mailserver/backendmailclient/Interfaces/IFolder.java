package mailserver.backendmailclient.Interfaces;

public interface IFolder {
    public boolean deleteFolder(String path);

    public boolean copyFolder(String dist, String source);

    public boolean moveFolder(String destination, String source);

    public String toStringJson(String path);

    public boolean writeJson(Object object, String path);
}
