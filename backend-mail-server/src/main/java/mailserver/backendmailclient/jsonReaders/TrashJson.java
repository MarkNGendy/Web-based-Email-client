package mailserver.backendmailclient.jsonReaders;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import mailserver.backendmailclient.classes.Folder;
import mailserver.backendmailclient.classes.Trash;
import mailserver.backendmailclient.interfaces.IFolder;

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
