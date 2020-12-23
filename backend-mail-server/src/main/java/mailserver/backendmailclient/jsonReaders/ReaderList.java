package mailserver.backendmailclient.jsonReaders;

import java.util.List;

public abstract class ReaderList<T> extends Json {
    protected List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setLsist(List<T> l) {
        this.list = l;
    }

    public abstract List<T> toList(String path);

}
