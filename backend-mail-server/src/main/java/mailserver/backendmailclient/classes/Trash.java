package mailserver.backendmailclient.classes;

import java.io.File;
import java.util.List;

import mailserver.backendmailclient.interfaces.IFolder;
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
        IFolder folder = new Folder();
        File trashFile = new File(trashFolder, "Trashfile.json");
        ReaderList<Trash> readlist = new TrashJson();
        readlist.toList(trashFile.getPath());
        List<Trash> trashlist = readlist.getList();

        int index = 0;
        for (Trash demo : trashlist) {
            if (demo.getID().equals(mailID)) {
                break;
            }
            index++;
        }
        trashlist.remove(index);
        readlist.setLsist(trashlist);
        folder.writeJson(readlist, trashFile.getPath());
    }
}
