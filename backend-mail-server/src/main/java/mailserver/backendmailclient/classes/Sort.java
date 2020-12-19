package mailserver.backendmailclient.classes;

import mailserver.backendmailclient.interfaces.ISort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort implements ISort {
    @Override
    public List<Mail> dateSorter(List<Mail> mails, String criteria) {
        return null;
    }

    @Override
    public List<Mail> subjectSorter(List<Mail> mails, String criteria) {
        Collections.sort(mails, Mail.MailSubjectComparator);
        return mails;
    }

    @Override
    public List<Mail> senderSorter(List<Mail> mails, String criteria) {
        return null;
    }

    @Override
    public List<Mail> receiverSorter(List<Mail> mails, String criteria) {
        return null;
    }

    @Override
    public List<Mail> importanceSorter(List<Mail> mails, String criteria) {
        return null;
    }

    @Override
    public List<Mail> bodySorter(List<Mail> mails, String criteria) {
        return null;
    }

    @Override
    public List<Mail> attachmentSorter(List<Mail> mails, String criteria) {
        return null;
    }
}

