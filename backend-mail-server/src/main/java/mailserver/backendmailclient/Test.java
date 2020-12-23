package mailserver.backendmailclient;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mailserver.backendmailclient.classes.Answer;
import mailserver.backendmailclient.classes.DemoUsers;
import mailserver.backendmailclient.classes.Mail;
import mailserver.backendmailclient.classes.User;
import mailserver.backendmailclient.controllers.MailBody;

public class Test {
    public static void main(String[] args) {
        /*
         * create emails; List<String> reciever = new ArrayList<>();
         * reciever.add("klsgadsffa@gmail.com"); reciever.add("Mark.N.Gendy@gmail.com");
         * reciever.add("sdadsasadas@hahaah"); reciever.add("Test.Email@gmail.com");
         * reciever.add("tests@gmail.com"); for (int i = 0; i < 10; i++) { MailBody
         * mailBody = new MailBody("abdo@gamil.com", reciever, "subject " +
         * String.valueOf(i) + " aloooo",
         * "ddasklgkljdsgajgh\nsdagsdghasdaf\nAdsgsdagsdag\nAGDsgsadgsadh\n" +
         * String.valueOf(i * 1000) + "\n       \n   sdafsdga\n", null); Mail mail = new
         * Mail(); Answer ans = mail.sendMail(mailBody);
         * System.out.println(ans.getSuccess() + ans.getAns()); try { Thread.sleep(100);
         * } catch (InterruptedException e) { System.out.println("no");
         * e.printStackTrace(); } } }
         */
        /*
         * Mail mail = new Mail(); File src = new
         * File("Server/Mark.N.Gendy@gmail.com/folders/Inbox"); int i = 0; for (String
         * item : src.list()) { if (item.equalsIgnoreCase("mails.json")) continue; if (i
         * == 0) { i++; continue; } Answer ans = mail.sendToTrash(src.getPath(), item);
         * System.out.println(ans.getSuccess() + " " + ans.getAns()); i++; try {
         * Thread.sleep(100); } catch (InterruptedException e) {
         * System.out.println("no"); e.printStackTrace(); } }
         */

        /*
         * Mail mail = new Mail(); File src = new
         * File("Server/Mark.N.Gendy@gmail.com/folders/Trash"); int i = 0; for (String
         * item : src.list()) { if (item.equalsIgnoreCase("mails.json") ||
         * item.equalsIgnoreCase("Trashfile.json")) continue; if (i > 3) { break; }
         * Answer ans = mail.restoreMail(src.getPath(), item);
         * System.out.println(ans.getSuccess() + " " + ans.getAns()); try {
         * Thread.sleep(100); } catch (InterruptedException e) {
         * System.out.println("no"); e.printStackTrace(); } i++; }
         */

        /*
         * Mail mail = new Mail(); File src = new
         * File("Server/sdadsasadas@hahaah/folders/Inbox"); int i = 0; for (String item
         * : src.list()) { if (item.equalsIgnoreCase("mails.json")) continue; if (i > 4)
         * { break; } Answer ans = mail.sendToTrash(src.getPath(), item);
         * System.out.println(ans.getSuccess() + " " + ans.getAns()); try {
         * Thread.sleep(100); } catch (InterruptedException e) {
         * System.out.println("no"); e.printStackTrace(); } i++; } i = 0; for (String
         * item : src.list()) { if (item.equalsIgnoreCase("mails.json")) continue; if (i
         * > 1) { break; } Answer ans = mail.deleteFromServer(src.getPath(), item);
         * System.out.println(ans.getSuccess() + " " + ans.getAns()); try {
         * Thread.sleep(100); } catch (InterruptedException e) {
         * System.out.println("no"); e.printStackTrace(); } i++; } src = new
         * File("Server/sdadsasadas@hahaah/folders/Trash"); i = 0; for (String item :
         * src.list()) { if (item.equalsIgnoreCase("mails.json") ||
         * item.equalsIgnoreCase("Trashfile.json")) continue; if (i > 2) { break; }
         * Answer ans = mail.deleteFromServer(src.getPath(), item);
         * System.out.println(ans.getSuccess() + " " + ans.getAns()); try {
         * Thread.sleep(100); } catch (InterruptedException e) {
         * System.out.println("no"); e.printStackTrace(); } i++; }
         */

        DemoUsers user = new DemoUsers("email@gamil.com", "pass", "name");
        User t = new User();
        Answer ans = t.signin(user);
        System.out.println(ans.getAns());
        ans = t.signup(user);
        System.out.println(ans.getAns());
        ans = t.signin(user);
        System.out.println(ans.getAns());
    }
}
