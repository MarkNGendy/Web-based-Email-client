package mailserver.backendmailclient.interfaces;

import mailserver.backendmailclient.classes.Mail;

import java.util.List;

public interface ISort {

    public List<Mail> dateSorter(List<Mail> mails, String criteria);

    public List<Mail> subjectSorter(List<Mail> mails, String criteria);

    public List<Mail> senderSorter(List<Mail> mails, String criteria);

    public List<Mail> receiverSorter(List<Mail> mails, String criteria);

    public List<Mail> importanceSorter(List<Mail> mails, String criteria);

    public List<Mail> bodySorter(List<Mail> mails, String criteria);

    public List<Mail> attachmentSorter(List<Mail> mails, String criteria);
}
