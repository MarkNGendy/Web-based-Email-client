package mailserver.backendmailclient.jsonReaders;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import mailserver.backendmailclient.classes.mail.*;

public class TrashJson extends Json {

    private List<Trash> list;

    public TrashJson() {
    }

    public TrashJson(List<Trash> l) {
        list = l;
    }

    @Override
    public List<Trash> readJson(String path) {
        String input = toStringJson(path);
        TrashJson temp = new Gson().fromJson(input, TrashJson.class);
        this.list = (temp == null) ? new ArrayList<>() : temp.list;
        return this.list;
    }
}
