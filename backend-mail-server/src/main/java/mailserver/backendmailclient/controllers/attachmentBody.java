package mailserver.backendmailclient.controllers;

public class attachmentBody {

    private String user;
    private String mailID;
    private String srcFolder;
    private String attachment;

    public attachmentBody(String user, String mailID, String srcFolder, String attachment){
        this.user = user;
        this.mailID = mailID;
        this.srcFolder = srcFolder;
        this.attachment = attachment;
    }

    public String getUser() {
        return user;
    }

    public String getMailID() {
        return mailID;
    }

    public String getSrcFolder() {
        return srcFolder;
    }

    public String getAttachment() {
        return attachment;
    }
}
