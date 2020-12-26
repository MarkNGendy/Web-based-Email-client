package mailserver.backendmailclient.classes.mail;

import java.util.Date;

import mailserver.backendmailclient.controllers.requestdata.MailBody;

public class mailAdapter {
    public Mail toMail(MailBody mailBody) {
        Mail ans = new Mail(mailBody.getSender(), mailBody.getReceivers(), mailBody.getSubject(), mailBody.getBody(),
                mailBody.getAttachments(), mailBody.getImportance());
        Long time = System.currentTimeMillis();
        String mailname = Long.toString(time);
        ans.setID(mailname);
        ans.setDate(new Date(time));
        return ans;
    }
}
