package mailserver.backendmailclient.Classes;

public class Trash {
    private String id;
    private String trashDate;

    public Trash(String id, String trashDate) {
        this.id = id;
        this.trashDate = trashDate;
    }

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public String getTrashDate() {
        return trashDate;
    }

    public void setTrashDate(String trashDate) {
        this.trashDate = trashDate;
    }
}
