package mailserver.backendmailclient.jsonReaders;

import java.io.*;

import com.google.gson.Gson;

public abstract class Json {

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

    protected String toStringJson(String path) {
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

    public abstract Object readJson(String path);
}
