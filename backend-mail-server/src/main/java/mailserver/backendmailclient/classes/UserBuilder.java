package mailserver.backendmailclient.classes;

import java.io.File;
import java.io.IOException;

public class UserBuilder {
    DemoUsers input;

    public UserBuilder(DemoUsers input) {
        this.input = input;
    }

    public boolean newUserBuilder() {
        String[] folders = { "Inbox", "Sent", "Draft", "Trash" };
        File userfolder = new File("Server", input.getemail());
        userfolder.mkdir();
        File contactsFolder = new File(userfolder, "contacts.json");
        try {
            contactsFolder.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
            return false;
        }
        userfolder = new File(userfolder, "folders");
        userfolder.mkdir();
        File changebleFolders = new File(userfolder, "UserFolders");
        changebleFolders.mkdir();

        for (String folder : folders) {
            File currfolder = new File(userfolder, folder);
            currfolder.mkdir();
            File mails = new File(currfolder, "mails.json");
            try {
                mails.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            if (folder.compareTo("Trash") == 0) {
                mails = new File(currfolder, "Trashfile.json");
                try {
                    mails.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }
}
