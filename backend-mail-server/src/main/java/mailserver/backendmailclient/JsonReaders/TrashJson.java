package mailserver.backendmailclient.JsonReaders;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import mailserver.backendmailclient.Classes.Folder;
import mailserver.backendmailclient.Classes.Trash;
import mailserver.backendmailclient.Interfaces.IFolder;

public class TrashJson extends ReaderList<Trash> {

    public TrashJson() {
    }

    public TrashJson(List<Trash> l) {
        list = l;
    }

    public void toList(String path) {
        IFolder folder = new Folder();
        String input = folder.toStringJson(path);
        TrashJson temp = new Gson().fromJson(input, TrashJson.class);
        this.list = (temp == null) ? new ArrayList<>() : temp.getList();
    }
}
