package mailserver.backendmailclient.classes.mail;

import java.io.File;
import java.io.IOException;
import java.util.List;

import java.util.Comparator;

import mailserver.backendmailclient.classes.*;
import mailserver.backendmailclient.classes.folder.*;
import mailserver.backendmailclient.controllers.requestdata.*;
import mailserver.backendmailclient.interfaces.*;
import mailserver.backendmailclient.jsonReaders.*;
import org.springframework.web.multipart.MultipartFile;

public class Mail extends DemoMail {
    private String body;
    private List<String> attachments;

    public Mail() {
    }

    public Mail(String sender, List<String> receivers, String subject, String body, List<String> attachments,
            int importance) {
        this.sender = sender;
        this.receivers = receivers;
        this.subject = subject;
        this.body = body;
        this.attachments = attachments;
        this.importance = importance;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private String wrong = "Something wrong!\nerror code: ";

    public Answer saveDraft(MailBody mailBody) throws IOException {
        mailAdapter mailAdapter = new mailAdapter();
        Mail mail = mailAdapter.toMail(mailBody);
        mail.setSrcFolder("Draft");
        FaçadeMail builderMail = new FaçadeMail();
        if (builderMail.buildMail(mail)) {
            return new Answer(true, "Draft saved successfully.");
        }
        return new Answer(false, wrong);
    }

    public Answer sendMail(MailBody mailBody, List<MultipartFile> files) throws IOException {
        mailAdapter mailAdapter = new mailAdapter();
        Mail mail = mailAdapter.toMail(mailBody);
        mail.setSrcFolder("Sent");
        FaçadeMail builderMail = new FaçadeMail();
        if (!builderMail.buildMail(mail)) {
            return new Answer(false, wrong + "Mail.Send.0001");
        }
        IFolder folder = new Folder();
        File source = new File("Server/" + mail.getSender() + "/folders/Sent/" + mail.getID());
        builderMail.sendFilesToMailFolder(mail, source, files);
        mail.setSrcFolder("Inbox");
        for (String receiver : mail.getReceivers()) {
            File inbox = new File("Server/" + receiver + "/folders/Inbox");
            File dest = new File(inbox, mail.getID());
            if (!folder.copyFolder(source, dest))
                return new Answer(false, wrong + "Mail.Send.0002");
            DemoMail demoMail = mail;
            builderMail.addToMailsFile(inbox, demoMail);
        }
        return new Answer(true, "Mail sent successfully.");
    }

    public Answer moveMail(String source, String destination, String mailID) {
        Answer bad = new Answer(false, wrong + "Mail.move.0005");
        File src = new File(source);
        File dest = new File(destination);
        File destmailfolder = new File(dest, mailID);
        File srcmailfolder = new File(src, mailID);
        IFolder folder = new Folder();
        if (!folder.moveFolder(srcmailfolder, destmailfolder))
            return bad;

        Index x = new Index();
        DemoMail target = x.getCopyThenDelete(src, mailID);
        JsonFactory factory = new JsonFactory();
        Json readerList = factory.jsfactory(ReaderType.MAILSLIST, x.getDemoList());
        readerList.writeJson(readerList, src.getPath() + "/mails.json");
        if (target == null)
            return bad;

        FaçadeMail builderMail = new FaçadeMail();
        builderMail.addToMailsFile(dest, target);
        return new Answer(true, "Mail moved successfully.");
    }

    public Answer copyMail(String source, String destination, String mailID) {
        Answer bad = new Answer(false, wrong + "Mail.move.0005");
        File src = new File(source);
        File dest = new File(destination);
        File destmailfolder = new File(dest, mailID);
        File srcmailfolder = new File(src, mailID);
        IFolder folder = new Folder();
        if (!folder.copyFolder(srcmailfolder, destmailfolder))
            return bad;

        Index x = new Index();
        x.getfromfile(src, mailID);
        DemoMail target = x.getDemoList().get(x.getIndex());
        JsonFactory factory = new JsonFactory();
        Json readerList = factory.jsfactory(ReaderType.MAILSLIST, x.getDemoList());
        readerList.writeJson(readerList, src.getPath() + "/mails.json");
        if (target == null)
            return bad;

        FaçadeMail builderMail = new FaçadeMail();
        builderMail.addToMailsFile(dest, target);
        return new Answer(true, "Mail moved successfully.");
    }

    public Answer sendToTrash(String source, String mailID) {
        File src = new File(source);
        File dest = new File(src.getParent(), "Trash");
        Answer ans = moveMail(source, dest.getPath(), mailID);
        if (!ans.getSuccess())
            return ans;

        File trashFile = new File(dest, "Trashfile.json");

        JsonFactory factory = new JsonFactory();
        Json readlist = factory.jsfactory(ReaderType.TRASH, null);
        List<Trash> trashlist = (List<Trash>) readlist.readJson(trashFile.getPath());

        Long time = System.currentTimeMillis();
        String trashtime = Long.toString(time);
        Trash item = new Trash(mailID, trashtime);

        trashlist.add(item);
        readlist = factory.jsfactory(ReaderType.TRASH, trashlist);
        readlist.writeJson(readlist, trashFile.getPath());

        return new Answer(true, "Mail sent to trash successfully.");
    }

    public Answer restoreMail(String current, String mailID) {
        Answer bad = new Answer(false, wrong);
        Index x = new Index();
        File currFolder = new File(current);
        x.getfromfile(currFolder, mailID);
        String source = x.getDemoList().get(x.getIndex()).getSrcFolder();
        File srcFolder = new File(currFolder.getParent(), source);
        if (currFolder.getName().equalsIgnoreCase("Trash")) {
            Trash t = new Trash();
            t.deleteFromTrashFile(currFolder.getPath(), mailID);
        }
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
        if (!folder.deleteFolder(targetfolder))
            return bad;
        Index x = new Index();
        x.removefromfile(currfolder, mailID);
        JsonFactory factory = new JsonFactory();
        Json readerList = factory.jsfactory(ReaderType.MAILSLIST, x.getDemoList());
        File mailsFile = new File(current, "mails.json");
        readerList.writeJson(readerList, mailsFile.getPath());
        if (currfolder.getName().equalsIgnoreCase("Trash")) {
            Trash t = new Trash();
            t.deleteFromTrashFile(currfolder.getPath(), mailID);
        }
        return new Answer(true, "Mail deleted successfully.");
    }

    @Override
    public String toString() {
        return "[ subject = " + this.subject + ", sender = " + this.sender + "]";
    }

    /* Comparators for sorting the list by Mail subject */
    public static Comparator<Mail> AMailSubjectComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            String MailSubject1 = m1.getSubject().toUpperCase();
            String MailSubject2 = m2.getSubject().toUpperCase();
            return MailSubject1.compareTo(MailSubject2);
        }
    };
    public static Comparator<Mail> DMailSubjectComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            String MailSubject1 = m1.getSubject().toUpperCase();
            String MailSubject2 = m2.getSubject().toUpperCase();
            return MailSubject2.compareTo(MailSubject1);
        }
    };

    /* Comparators for sorting the list by Mail sender */
    public static Comparator<Mail> AMailSenderComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            String MailSender1 = m1.getSender().toUpperCase();
            String MailSender2 = m2.getSender().toUpperCase();
            return MailSender1.compareTo(MailSender2);
        }
    };
    public static Comparator<Mail> DMailSenderComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            String MailSender1 = m1.getSender().toUpperCase();
            String MailSender2 = m2.getSender().toUpperCase();
            return MailSender2.compareTo(MailSender1);
        }
    };

    /* Comparators for sorting the list by Mail receivers */
    public static Comparator<Mail> AMailReceiversComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            int MailReceivers1 = m1.getReceivers().size();
            int MailReceivers2 = m2.getReceivers().size();

            return MailReceivers1 - MailReceivers2;
        }
    };
    public static Comparator<Mail> DMailReceiversComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            int MailReceivers1 = m1.getReceivers().size();
            int MailReceivers2 = m2.getReceivers().size();
            return MailReceivers2 - MailReceivers1;
        }
    };

    /* Comparators for sorting the list by Mail importance */
    public static Comparator<Mail> AMailImportanceComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            int MailImp1 = m1.getImportance();
            int MailImp2 = m2.getImportance();
            return MailImp1 - MailImp2;
        }
    };
    public static Comparator<Mail> DMailImportanceComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            int MailImp1 = m1.getImportance();
            int MailImp2 = m2.getImportance();
            return MailImp2 - MailImp1;
        }
    };

    /* Comparators for sorting the list by Mail time */
    public static Comparator<Mail> AMailDateComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            long MailTime1 = Long.valueOf(m1.getID());
            long MailTime2 = Long.valueOf(m2.getID());
            return (int) (MailTime1 - MailTime2);
        }
    };
    public static Comparator<Mail> DMailDateComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            long MailTime1 = Long.valueOf(m1.getID());
            long MailTime2 = Long.valueOf(m2.getID());
            return (int) (MailTime2 - MailTime1);
        }
    };

    /* Comparators for sorting the list by Mail attachments */
    public static Comparator<Mail> AMailAttachmentsComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            int MailAttachments1 = m1.getAttachments().size();
            int MailAttachments2 = m2.getAttachments().size();
            return MailAttachments1 - MailAttachments2;
        }
    };
    public static Comparator<Mail> DMailAttachmentsComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            int MailAttachments1 = m1.getAttachments().size();
            int MailAttachments2 = m2.getAttachments().size();
            return MailAttachments2 - MailAttachments1;
        }
    };

    /* Comparators for sorting the list by Mail body */
    public static Comparator<Mail> AMailBodyComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            int MailBody1 = m1.getBody().length();
            int MailBody2 = m2.getBody().length();
            return MailBody1 - MailBody2;
        }
    };
    public static Comparator<Mail> DMailBodyComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            int MailBody1 = m1.getBody().length();
            int MailBody2 = m2.getBody().length();
            return MailBody2 - MailBody1;
        }
    };

}
