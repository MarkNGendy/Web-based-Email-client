package mailserver.backendmailclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mailserver.backendmailclient.classes.Answer;
import mailserver.backendmailclient.classes.Mail;
import mailserver.backendmailclient.controllers.MailBody;

public class test {
    public static void main(String[] args) {
        List<String> receiver = new ArrayList<>();
        receiver.add("abdo@demo.com");
        MailBody mailBody = new MailBody("mina@demo.com", receiver, "fdsjgksdfhl", "dsfj;hgdflh", null, 5);
        Mail mail = new Mail();
        Answer ans;
        try {
            ans = mail.saveDraft(mailBody);
            System.out.println(ans.getAns());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
