package mailserver.backendmailclient.jsonReaders;

import java.util.List;

public abstract class ReaderList<T> {
    protected List<T> list;

    protected ReaderList() {
    }

    protected ReaderList(List<T> l) {
        this.list = l;
    }

    public List<T> getList() {
        return list;
    }

    public void setLsist(List<T> l) {
        this.list = l;
    }

    public abstract void toList(String path);

}
