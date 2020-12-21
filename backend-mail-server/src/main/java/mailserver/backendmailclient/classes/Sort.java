package mailserver.backendmailclient.classes;

import mailserver.backendmailclient.interfaces.ISort;

import java.util.Collections;
import java.util.List;

public class Sort implements ISort {
    @Override
    public List<Mail> dateSorter(List<Mail> mails, String criteria) {
        if (criteria.equalsIgnoreCase("DESCENDING")) {
            Collections.sort(mails, Mail.DMailDateComparator);
            return mails;
        } else if (criteria.equalsIgnoreCase("ASCENDING")){
            Collections.sort(mails, Mail.AMailDateComparator);
            return mails;
        }
        else {
            return mails;
        }
    }

    @Override
    public List<Mail> subjectSorter(List<Mail> mails, String criteria) {
        if (criteria.equalsIgnoreCase("DESCENDING")) {
            Collections.sort(mails, Mail.DMailSubjectComparator);
            return mails;
        } else if (criteria.equalsIgnoreCase("ASCENDING")){
            Collections.sort(mails, Mail.AMailSubjectComparator);
            return mails;
        }
        else {
            return mails;
        }
    }

    @Override
    public List<Mail> senderSorter(List<Mail> mails, String criteria) {
        if (criteria.equalsIgnoreCase("DESCENDING")) {
            Collections.sort(mails, Mail.DMailSenderComparator);
            return mails;
        } else if (criteria.equalsIgnoreCase("ASCENDING")){
            Collections.sort(mails, Mail.AMailSenderComparator);
            return mails;
        }
        else {
            return mails;
        }
    }

    @Override
    public List<Mail> receiversSorter(List<Mail> mails, String criteria) {
        if (criteria.equalsIgnoreCase("DESCENDING")) {
            Collections.sort(mails, Mail.DMailReceiversComparator);
            return mails;
        } else if (criteria.equalsIgnoreCase("ASCENDING")){
            Collections.sort(mails, Mail.AMailReceiversComparator);
            return mails;
        }
        else {
            return mails;
        }
    }

    @Override
    public List<Mail> importanceSorter(List<Mail> mails, String criteria) {
        if (criteria.equalsIgnoreCase("DESCENDING")) {
            Collections.sort(mails, Mail.DMailImportanceComparator);
            return mails;
        } else if (criteria.equalsIgnoreCase("ASCENDING")){
            Collections.sort(mails, Mail.AMailImportanceComparator);
            return mails;
        }
        else {
            return mails;
        }
    }

    @Override
    public List<Mail> bodySorter(List<Mail> mails, String criteria) {
        if (criteria.equalsIgnoreCase("DESCENDING")) {
            Collections.sort(mails, Mail.DMailBodyComparator);
            return mails;
        } else if (criteria.equalsIgnoreCase("ASCENDING")){
            Collections.sort(mails, Mail.AMailBodyComparator);
            return mails;
        }
        else {
            return mails;
        }
    }

    @Override
    public List<Mail> attachmentSorter(List<Mail> mails, String criteria) {
        if (criteria.equalsIgnoreCase("DESCENDING")) {
            Collections.sort(mails, Mail.DMailAttachmentsComparator);
            return mails;
        } else if (criteria.equalsIgnoreCase("ASCENDING")){
            Collections.sort(mails, Mail.AMailAttachmentsComparator);
            return mails;
        }
        else {
            return mails;
        }
    }

    @Override
    public List<Contact> contactsSorter(List<Contact> contacts, String criteria) {
        if (criteria.equalsIgnoreCase("DESCENDING")) {
            Collections.sort(contacts, Contact.DContactsComparator);
            return contacts;
        } else if (criteria.equalsIgnoreCase("ASCENDING")){
            Collections.sort(contacts, Contact.AContactsComparator);
            return contacts;
        }
        else {
            return contacts;
        }
    }
}

