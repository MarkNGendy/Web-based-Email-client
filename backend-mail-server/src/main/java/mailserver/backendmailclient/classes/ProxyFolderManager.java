package mailserver.backendmailclient.classes;

import java.util.List;

public class ProxyFolderManager {

    User user = new User();

    public void createFolder(String user, String name) {
        this.user.addFolder(user, name);
    }

    public void deleteFolder(String user, String name) {
        this.user.removeFolder(user, name);
    }

    public void renameFolder(String user, String oldName, String newName) {
        this.user.renameFolder(user, oldName, newName);
    }

    public List<String> foldersList(String username) {
        return this.user.getFolderList(username);
    }

}
