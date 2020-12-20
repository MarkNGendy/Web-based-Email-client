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

    public Mail(String sender, List<String> receivers, String subject, String body, List<File> attachments) {
        this.sender = sender;
        this.receivers = receivers;
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

    private String wrong = "Something wrong!\nerror code: ";

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
            return new Answer(false, wrong + "Mail.Send.0001");
        }
        IFolder folder = new Folder();
        File source = new File("server/" + mail.getSender() + "/folders/Sent/" + mail.getID());
        mail.setSrcFolder("Inbox");
        for (String reciever : mail.getReceivers()) {
            File inbox = new File("server/" + reciever + "/folders/Inbox");
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
        ReaderList<DemoMail> readerList = new MailsJson(x.getDemoList());
        folder.writeJson(readerList, src.getPath() + "/mails.json");
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
        ReaderList<DemoMail> readerList = new MailsJson(x.getDemoList());
        File mailsFile = new File(current, "mails.json");
        folder.writeJson(readerList, mailsFile.getPath());
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

            // ascending order
            return MailSubject1.compareTo(MailSubject2);

            // descending order
            // return MailSubject2.compareTo(MailSubject1);
        }
    };
    public static Comparator<Mail> DMailSubjectComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            String MailSubject1 = m1.getSubject().toUpperCase();
            String MailSubject2 = m2.getSubject().toUpperCase();

            // descending order
            // return MailSubject1.compareTo(MailSubject2);

            // ascending order
            return MailSubject2.compareTo(MailSubject1);
        }
    };

    /* Comparators for sorting the list by Mail sender */
    public static Comparator<Mail> AMailSenderComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            String MailSender1 = m1.getSender().toUpperCase();
            String MailSender2 = m2.getSender().toUpperCase();

            // ascending order
            return MailSender1.compareTo(MailSender2);

            // descending order
            // return MailSender2.compareTo(MailSender1);
        }
    };
    public static Comparator<Mail> DMailSenderComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            String MailSender1 = m1.getSender().toUpperCase();
            String MailSender2 = m2.getSender().toUpperCase();

            // ascending order
            // return MailSender1.compareTo(MailSender2);

            // descending order
            return MailSender2.compareTo(MailSender1);
        }
    };

    /* Comparators for sorting the list by Mail receivers */
    public static Comparator<Mail> AMailReceiversComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            int MailReceivers1 = m1.getReceivers().size();
            int MailReceivers2 = m2.getReceivers().size();

            // ascending order
            return MailReceivers1 - MailReceivers2;

            // descending order
            // return MailReceivers2 - MailReceivers1;
        }
    };
    public static Comparator<Mail> DMailReceiversComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            int MailReceivers1 = m1.getReceivers().size();
            int MailReceivers2 = m2.getReceivers().size();

            // ascending order
            // return MailReceivers1 - MailReceivers2;

            // descending order
            return MailReceivers2 - MailReceivers1;
        }
    };

    /* Comparators for sorting the list by Mail importance */
    public static Comparator<Mail> AMailImportanceComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            int MailImp1 = m1.getImportance();
            int MailImp2 = m2.getImportance();

            // ascending order
            return MailImp1 - MailImp2;

            // descending order
            // return MailImp2 - MailImp1;
        }
    };
    public static Comparator<Mail> DMailImportanceComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            int MailImp1 = m1.getImportance();
            int MailImp2 = m2.getImportance();

            // ascending order
            // return MailImp1 - MailImp2;

            // descending order
            return MailImp2 - MailImp1;
        }
    };

    /* Comparators for sorting the list by Mail time */
    public static Comparator<Mail> AMailDateComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            long MailTime1 = Long.valueOf(m1.getID());
            long MailTime2 = Long.valueOf(m2.getID());

            // ascending order
            return (int) (MailTime1 - MailTime2);

            // descending order
            // return (int)(MailTime2 - MailTime1);
        }
    };
    public static Comparator<Mail> DMailDateComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            long MailTime1 = Long.valueOf(m1.getID());
            long MailTime2 = Long.valueOf(m2.getID());

            // ascending order
            // return (int)(MailTime1 - MailTime2);

            // descending order
            return (int) (MailTime2 - MailTime1);
        }
    };

    /* Comparators for sorting the list by Mail attachments */
    public static Comparator<Mail> AMailAttachmentsComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            int MailAttachments1 = m1.getAttachments().size();
            int MailAttachments2 = m2.getAttachments().size();

            // ascending order
            return MailAttachments1 - MailAttachments2;

            // descending order
            // return MailAttachments2 - MailAttachments1;
        }
    };
    public static Comparator<Mail> DMailAttachmentsComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            int MailAttachments1 = m1.getAttachments().size();
            int MailAttachments2 = m2.getAttachments().size();

            // ascending order
            // return MailAttachments1 - MailAttachments2;

            // descending order
            return MailAttachments2 - MailAttachments1;
        }
    };

    /* Comparators for sorting the list by Mail body */
    public static Comparator<Mail> AMailBodyComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            int MailBody1 = m1.getBody().length();
            int MailBody2 = m2.getBody().length();

            // ascending order
            return MailBody1 - MailBody2;

            // descending order
            // return MailBody2 - MailBody1;
        }
    };
    public static Comparator<Mail> DMailBodyComparator = new Comparator<Mail>() {

        public int compare(Mail m1, Mail m2) {
            int MailBody1 = m1.getBody().length();
            int MailBody2 = m2.getBody().length();

            // ascending order
            // return MailBody1 - MailBody2;

            // descending order
            return MailBody2 - MailBody1;
        }
    };

}
