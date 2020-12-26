package mailserver.backendmailclient.controllers.requestdata;

import mailserver.backendmailclient.classes.mail.*;

import java.util.List;

public class FSSBody {
    private List<Mail> list;
    private String field;
    private String criteria;

    public FSSBody(List<Mail> list, String field, String criteria) {
        this.list = list;
        this.field = field;
        this.criteria = criteria;
    }

    public List<Mail> getList() {
        return list;
    }

    public String getField() {
        return field;
    }

    public String getCriteria() {
        return criteria;
    }

}