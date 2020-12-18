package mailserver.backendmailclient.classes;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

import mailserver.backendmailclient.interfaces.IFolder;
import mailserver.backendmailclient.jsonReaders.*;

public class BuilderMail {
    public boolean buildMail(Mail mail) {
        File mainfolder = new File("server/" + mail.getSender() + "/folders/" + mail.getSrcFolder());
        File mailfolder = new File(mainfolder, mail.getID());
        mailfolder.mkdir();
        File attach = new File(mailfolder, "attachements");
        attach.mkdir();

        DemoMail temp = mail;
        addToMailsFile(mainfolder, temp);

        if (!createMAilFile(mailfolder, mail))
            return false;

        if (mail.getAttachments() != null) {
            for (File a : mail.getAttachments()) {
                File t = new File(attach, a.getName());
                try {
                    Files.copy(a.toPath(), t.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    public void addToMailsFile(File mainfolder, DemoMail temp) {
        IFolder folder = new Folder();
        File mails = new File(mainfolder, "mails.json");
        ReaderList<DemoMail> readlist = new MailsJson();
        readlist.toList(mails.getPath());
        List<DemoMail> userslist = readlist.getList();
        userslist.add(temp);
        readlist.setLsist(userslist);
        folder.writeJson(readlist, mails.getPath());
    }

    private boolean createMAilFile(File mailfolder, Mail mail) {
        IFolder folder = new Folder();
        File mailfile = new File(mailfolder, "mailfile.json");
        try {
            mailfile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        folder.writeJson(mail, mailfile.getPath());
        return true;
    }

}
