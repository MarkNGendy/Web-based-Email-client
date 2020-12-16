package mailserver.backendmailclient.Classes;

public class Answer {
    private boolean success;
    private String ans;

    public Answer(boolean success, String ans) {
        this.success = success;
        this.ans = ans;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public void setSuccess(boolean s) {
        this.success = s;
    }

    public boolean getSuccess() {
        return success;
    }
}
