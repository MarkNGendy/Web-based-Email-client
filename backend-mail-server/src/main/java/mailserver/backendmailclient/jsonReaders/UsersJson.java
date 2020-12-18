package mailserver.backendmailclient.jsonReaders;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import mailserver.backendmailclient.classes.DemoUsers;
import mailserver.backendmailclient.classes.Folder;
import mailserver.backendmailclient.interfaces.IFolder;

public class UsersJson extends ReaderList<DemoUsers> {

    public UsersJson() {
    }

    public UsersJson(List<DemoUsers> l) {
        list = l;
    }

    public void toList(String path) {
        IFolder folder = new Folder();
        String input = folder.toStringJson(path);
        UsersJson temp = new Gson().fromJson(input, UsersJson.class);
        this.list = (temp == null) ? new ArrayList<>() : temp.getList();
    }
}
