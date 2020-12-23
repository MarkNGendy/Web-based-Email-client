package mailserver.backendmailclient.jsonReaders;

import com.google.gson.Gson;

import mailserver.backendmailclient.classes.Mail;

public class MailJson extends Json {

    @Override
    public Mail readJson(String path) {
        String input = toStringJson(path);
        Mail temp = new Gson().fromJson(input, Mail.class);
        return temp;
    }

}
