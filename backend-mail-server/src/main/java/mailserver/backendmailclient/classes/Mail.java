package mailserver.backendmailclient.classes;

import java.io.File;
import java.util.List;
import java.util.Comparator;

import mailserver.backendmailclient.interfaces.IFolder;
import mailserver.backendmailclient.jsonReaders.*;
import mailserver.backendmailclient.controllers.MailBody;

public class Mail extends DemoMail {
    private String body;
    private List<File> attachments;

    public Mail() {
    }

    public Mail(String sender, List<String> reciever, String subject, String body, List<File> attachments) {
        this.sender = sender;
        this.reciever = reciever;
        this.subject = subject;
        this.body = body;
        this.attachments = attachments;
    }

    public List<File> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<File> attachments) {
        this.attachments = attachments;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private String wrong = "Something wrong!";

    public Answer saveDraft(MailBody mailBody) {
        Mail mail = mailBody.toMail();
        mail.setSrcFolder("Draft");
        BuilderMail builderMail = new BuilderMail();
        if (builderMail.buildMail(mail)) {
            return new Answer(true, "Draft saved successfully.");
        }
        return new Answer(false, wrong);
    }

    public Answer sendMail(MailBody mailBody) {
        Mail mail = mailBody.toMail();
        mail.setSrcFolder("Sent");
        BuilderMail builderMail = new BuilderMail();
        if (!builderMail.buildMail(mail)) {
            return new Answer(false, wrong);
        }
        IFolder folder = new Folder();
        File source = new File("server/" + mail.getSender() + "/folders/Sent/" + mail.getID());
        mail.setSrcFolder("Inbox");
        for (String reciever : mail.getRecievers()) {
            File inbox = new File("server/" + reciever + "/folders/Inbox");
            File dest = new File(inbox, mail.getID());
            if (!folder.copyFolder(dest.getPath(), source.getPath()))
                return new Answer(false, wrong);
            DemoMail demoMail = mail;
            builderMail.addToMailsFile(inbox, demoMail);
        }
        return new Answer(true, "Mail sent successfully.");
    }

    public Answer moveMail(String source, String destination, String mailID) {
        Answer bad = new Answer(false, wrong);
        File src = new File(source);
        File dest = new File(destination);
        File destmailfolder = new File(dest, mailID);
        File srcmailfolder = new File(src, mailID);
        IFolder folder = new Folder();
        if (!folder.copyFolder(destmailfolder.getPath(), srcmailfolder.getPath()))
            return bad;

        Index x = new Index();
        DemoMail target = x.getCopyThenDelete(src, mailID);
        if (target == null)
            return bad;

        BuilderMail builderMail = new BuilderMail();
        builderMail.addToMailsFile(dest, target);
        return new Answer(true, "Mail moved successfully.");
    }

    public Answer sendToTrash(String source, String mailID) {
        IFolder folder = new Folder();
        File src = new File(source);
        File dest = new File(src.getParent(), "Trash");
        Answer ans = moveMail(source, dest.getPath(), mailID);
        if (!ans.getSuccess())
            return ans;

        File trashFile = new File(dest, "Trashfile.json");
        ReaderList<Trash> readlist = new TrashJson();
        readlist.toList(trashFile.getPath());
        List<Trash> trashlist = readlist.getList();

        Long time = System.currentTimeMillis();
        String trashtime = Long.toString(time);
        Trash item = new Trash(mailID, trashtime);

        trashlist.add(item);
        readlist.setLsist(trashlist);
        folder.writeJson(readlist, trashFile.getPath());

        return new Answer(true, "Mail sent to trash successfully.");
    }

    public Answer restoreMail(String current, String mailID) {
        Answer bad = new Answer(false, wrong);
        Index x = new Index();
        File currFolder = new File(current);
        x.getfromfile(currFolder, mailID);
        String source = x.getDemoList().get(x.getIndex()).getSrcFolder();
        File srcFolder = new File(currFolder.getParent(), source);
        Answer ans = moveMail(current, srcFolder.getPath(), mailID);
        if (ans.getSuccess())
            return new Answer(true, "Mail restored successfully.");
        else
            return bad;
    }

    public Answer deleteFromServer(String current, String mailID) {
        Answer bad = new Answer(false, wrong);
        File currfolder = new File(current);
        File targetfolder = new File(current, mailID);
        IFolder folder = new Folder();
        if (!folder.deleteFolder(targetfolder.getPath()))
            return bad;
        Index x = new Index();
        x.removefromfile(currfolder, mailID);
        ReaderList<DemoMail> readerList = new MailsJson(x.getDemoList());
        File mailsFile = new File(current, "mails.json");
        folder.writeJson(readerList, mailsFile.getPath());
        if (currfolder.getName().equals("Trash")) {
            File trashFile = new File(currfolder, "Trashfile.json");
            ReaderList<Trash> readTrash = new TrashJson();
            readTrash.toList(trashFile.getPath());
            List<Trash> trashlist = readTrash.getList();

            int index = 0;
            for (Trash demo : trashlist) {
                if (demo.getID().equals(mailID)) {
                    trashlist.remove(index);
                    break;
                }
                index++;
            }
            readTrash.setLsist(trashlist);
            folder.writeJson(readTrash, trashFile.getPath());
        }
        return new Answer(true, "Mail deleted successfully.");
    }
    @Override
    public String toString(){
        return "[ subject = " + this.subject + ", sender = " + this.sender + "]";
    }

    /*Comparator for sorting the list by Mail subject*/
    public static Comparator<Mail> MailSubjectComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            String MailSubject2 = m1.getSubject().toUpperCase();
            String MailSubject1 = m2.getSubject().toUpperCase();

            //ascending order
            return MailSubject1.compareTo(MailSubject2);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }
    };
}
