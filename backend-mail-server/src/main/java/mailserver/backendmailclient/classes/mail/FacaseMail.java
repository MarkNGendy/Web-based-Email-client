package mailserver.backendmailclient.classes.mail;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import mailserver.backendmailclient.jsonReaders.*;
import org.springframework.web.multipart.MultipartFile;

public class FacaseMail {
    public boolean buildMail(Mail mail) throws IOException {
        File mainfolder = new File("Server/" + mail.getSender() + "/folders/" + mail.getSrcFolder());
        File mailfolder = new File(mainfolder, mail.getID());
        mailfolder.mkdir();
        File attach = new File(mailfolder, "attachments");
        attach.mkdir();

        DemoMail temp = mail;
        addToMailsFile(mainfolder, temp);

        if (!createMAilFile(mailfolder, mail))
            return false;

        return true;
    }

    void sendFilesToMailFolder(Mail mail, File mailfolder, List<MultipartFile> files) throws IOException {
        if (mail.getAttachments() != null) {
            for (MultipartFile f : files) {
                byte[] bytes = f.getBytes();
                Path p = Path.of(mailfolder.getPath() + "/attachments/" + f.getOriginalFilename());
                Files.write(p, bytes);
            }
        }
    }

    public void addToMailsFile(File mainfolder, DemoMail temp) {
        File mails = new File(mainfolder, "mails.json");
        Json readlist = new MailsListJson();
        List<DemoMail> userslist = (List<DemoMail>) readlist.readJson(mails.getPath());
        userslist.add(temp);
        readlist = new MailsListJson(userslist);
        readlist.writeJson(readlist, mails.getPath());
    }

    private boolean createMAilFile(File mailfolder, Mail mail) {
        File mailfile = new File(mailfolder, "mailfile.json");
        try {
            mailfile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        Json write = new MailJson();
        write.writeJson(mail, mailfile.getPath());
        return true;
    }

}
