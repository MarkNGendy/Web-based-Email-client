package mailserver.backendmailclient.Interfaces;

import java.util.List;

public interface IFolder {
    public boolean deleteFolder(String path);

    public boolean copyFolder(String dist, String source);

    public boolean moveFolder(String destination, String source);

    public Object readJson(String path);

    public boolean writeJson(Object object, String path);
}
