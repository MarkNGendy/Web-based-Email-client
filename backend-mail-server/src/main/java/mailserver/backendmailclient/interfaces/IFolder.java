package mailserver.backendmailclient.interfaces;

import java.io.File;

public interface IFolder {
    public boolean deleteFolder(File src);

    public boolean copyFolder(File src, File dest);

    public boolean moveFolder(File source, File destination);

}
