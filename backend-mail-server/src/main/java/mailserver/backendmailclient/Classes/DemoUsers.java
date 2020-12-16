package mailserver.backendmailclient.Classes;

public class DemoUsers {
    private String email = "";
    private String password = "";
    private String username = "";

    public DemoUsers(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public DemoUsers(String email, String password, String name) {
        this.password = password;
        this.email = email;
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }
}
