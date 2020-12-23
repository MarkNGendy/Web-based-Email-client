package mailserver.backendmailclient.classes;

import java.io.File;
import java.util.List;

import mailserver.backendmailclient.jsonReaders.*;

public class Index {
    private List<DemoMail> demoList;
    private int i;

    public Index() {
    }

    public Index(int index, List<DemoMail> demoList) {
        this.i = index;
        this.demoList = demoList;
    }

    public int getIndex() {
        return i;
    }

    public List<DemoMail> getDemoList() {
        return demoList;
    }

    public void getfromfile(File folder, String mailID) {
        File mailsFile = new File(folder, "mails.json");

        JsonFactory factory = new JsonFactory();
        Json readlist = factory.jsfactory(ReaderType.MAILSLIST, null);
        List<DemoMail> mailslist = (List<DemoMail>) readlist.readJson(mailsFile.getPath());

        int index = 0;
        for (DemoMail demo : mailslist) {
            if (demo.getID().equals(mailID)) {
                break;
            }
            index++;
        }
        this.i = index;
        this.demoList = mailslist;
    }

    public void removefromfile(File folder, String mailID) {
        getfromfile(folder, mailID);
        if (this.i <= this.demoList.size())
            this.demoList.remove(this.i);
    }

    public DemoMail getCopyThenDelete(File folder, String mailID) {
        getfromfile(folder, mailID);
        DemoMail ans = null;
        if (this.i < this.demoList.size()) {
            ans = demoList.get(this.i);
            demoList.remove(this.i);
        }
        return ans;
    }

}
