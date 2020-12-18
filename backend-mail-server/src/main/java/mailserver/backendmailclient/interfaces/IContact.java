package mailserver.backendmailclient.interfaces;

import java.util.List;

public interface IContact {
    public boolean setname(String name);

    public String getname();

    public boolean removeemail(int index);

    public boolean addemail(String email);

    public List<String> getemailslist();
}
