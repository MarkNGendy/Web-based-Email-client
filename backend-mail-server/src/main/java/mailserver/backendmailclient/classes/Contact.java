package mailserver.backendmailclient.classes;

import java.util.*;

public class Contact {
    private String username;
    private List<String> email;

    public Contact(String username, List<String> email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }
}
