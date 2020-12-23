package mailserver.backendmailclient.jsonReaders;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import mailserver.backendmailclient.classes.DemoUsers;

public class UsersJson extends Json {

    private List<DemoUsers> list;

    public UsersJson() {
    }

    public UsersJson(List<DemoUsers> l) {
        list = l;
    }

    @Override
    public List<DemoUsers> readJson(String path) {
        String input = toStringJson(path);
        UsersJson temp = new Gson().fromJson(input, UsersJson.class);
        this.list = (temp == null) ? new ArrayList<>() : temp.list;
        return this.list;
    }
}
