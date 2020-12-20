package mailserver.backendmailclient;

import mailserver.backendmailclient.classes.Mail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class main {
    public  static void main(String[] args) {
        List<Mail> test = new ArrayList<>();
        test.add(new Mail("s1",null,"mina",null,null));
        test.add(new Mail("s2",null,"Ali",null,null));
        test.add(new Mail("s3",null,"yousef",null,null));
        test.add(new Mail("s4",null,"mark",null,null));
        Collections.sort(test,Mail.DMailSubjectComparator);
        for (Mail m : test){
            System.out.println(m);
        }
    }
}
