package mailserver.backendmailclient.Classes;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

import com.google.gson.Gson;

import mailserver.backendmailclient.Interfaces.IFolder;

public class Folder implements IFolder {

    @Override
    public boolean deleteFolder(String source) {
        File src = new File(source);
        if (src.isDirectory()) {
            String[] files = src.list();
            if (files.length == 0) {
                src.delete();
            } else {
                for (String filename : files) {
                    File newfile = new File(src, filename);
                    deleteFolder(newfile.getPath());
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
    public boolean copyFolder(String destination, String source) {
        File dest = new File(destination);
        File src = new File(source);
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }
            String[] files = src.list();
            for (String filename : files) {
                File srcFile = new File(src, filename);
                File destFile = new File(dest, filename);
                if (!copyFolder(srcFile.getPath(), destFile.getPath()))
                    return false;
            }
        } else {
            try {
                Files.copy(src.toPath(), dest.toPath());
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean moveFolder(String destination, String source) {
        if (copyFolder(destination, source) && deleteFolder(source))
            return true;
        return false;
    }

    @Override
    public Object readJson(String path) {
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
        return new Gson().fromJson(jsonString, Object.class);
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
