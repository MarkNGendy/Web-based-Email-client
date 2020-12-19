package mailserver.backendmailclient.controllers;

import mailserver.backendmailclient.classes.Mail;

import java.util.List;

public class FilterSortBody {
    private List<Mail> list;
    private String field;
    private String criteria;


    public FilterSortBody(List<Mail> list, String field, String criteria) {
        this.list = list;
        this.field = field;
        this.criteria = criteria;
    }

    public List<Mail> getList() { return list; }

    public String getField() { return field; }

    public String getCriteria() { return criteria; }

}