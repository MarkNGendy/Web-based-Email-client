package mailserver.backendmailclient.classes;

public class ProxyFolderManager {

    User user = new User();

    public void CreateFolder(String user, String name){
        this.user.addFolder(user,name);
    }

    public void deleteFolder(String user, String name){
        this.user.removeFolder(user,name);
    }

    public void renameFolder(String user, String oldName, String newName){
        this.user.renameFolder(user,oldName,newName);
    }

}
