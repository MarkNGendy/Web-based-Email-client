package mailserver.backendmailclient.classes;

import java.io.*;
import java.nio.file.Files;

import com.google.gson.Gson;
import mailserver.backendmailclient.interfaces.IFolder;

public class Folder implements IFolder {

    @Override
    public boolean deleteFolder(File src) {
        if (src.isDirectory()) {
            String[] files = src.list();
            if (files.length == 0) {
                src.delete();
            } else {
                for (String filename : files) {
                    File newfile = new File(src, filename);
                    deleteFolder(newfile);
                    if (src.exists())
                        src.delete();
                }
            }
        } else {
            src.delete();
        }
        return true;
    }

    @Override
    public boolean copyFolder(File src, File dest) {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }
            String[] files = src.list();
            for (String filename : files) {
                File srcFile = new File(src, filename);
                File destFile = new File(dest, filename);
                if (!copyFolder(srcFile, destFile)) {
                    System.out.println("folder.copy.0003");
                    return false;
                }
            }
        } else {
            try {
                Files.copy(src.toPath(), dest.toPath());
            } catch (IOException e) {
                System.out.println("folder.copy.0004");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean moveFolder(File source, File destination) {
        return copyFolder(source, destination) && deleteFolder(source);
    }

    @Override
    public String toStringJson(String path) {
        String jsonString = "";
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while (true) {
                try {
                    if ((line = reader.readLine()) == null)
                        break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            if (stringBuilder.length() > 0)
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            jsonString = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    @Override
    public boolean writeJson(Object object, String path) {
        String jsonString = new Gson().toJson(object);
        File file = new File(path);
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(jsonString);
            printWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
