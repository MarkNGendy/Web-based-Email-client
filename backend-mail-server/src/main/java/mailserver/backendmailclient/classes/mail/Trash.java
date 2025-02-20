package mailserver.backendmailclient.classes.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mailserver.backendmailclient.jsonReaders.*;

public class Trash {
    private String id;
    private String trashDate;

    public Trash() {
    }

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

    public void deleteFromTrashFile(String trashFolder, String mailID) {
        File trashFile = new File(trashFolder, "Trashfile.json");

        JsonFactory factory = new JsonFactory();
        Json readlist = factory.jsfactory(ReaderType.TRASH, null);
        List<Trash> trashlist = (List<Trash>) readlist.readJson(trashFile.getPath());

        int index = 0;
        for (Trash demo : trashlist) {
            if (demo.getID().equals(mailID)) {
                break;
            }
            index++;
        }
        trashlist.remove(index);
        readlist = factory.jsfactory(ReaderType.TRASH, trashlist);
        readlist.writeJson(readlist, trashFile.getPath());
    }

    public void day30(String user) {
        List<String> targetmails = new ArrayList<>();
        File trashFolder = new File("Server/" + user + "/folders/Trash/");
        File trashFile = new File(trashFolder, "Trashfile.json");

        JsonFactory factory = new JsonFactory();
        Json readlist = factory.jsfactory(ReaderType.TRASH, null);
        List<Trash> trashlist = (List<Trash>) readlist.readJson(trashFile.getPath());

        long _30days = (long) 2.592e9;
        Long currenttime = System.currentTimeMillis();
        for (Trash trash : trashlist) {
            if (currenttime - Long.parseLong(trash.getTrashDate()) > _30days) {
                targetmails.add(trash.getID());
            }
        }
        Mail mail = new Mail();
        for (String id : targetmails) {
            mail.deleteFromServer(trashFolder.getPath(), id);
        }
    }
}
