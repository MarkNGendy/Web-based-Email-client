package mailserver.backendmailclient.Classes;

import mailserver.backendmailclient.Interfaces.IUser;

public class test {
    public static void main(String[] args) {
        DemoUsers demo = new DemoUsers("klsgadsffa@gmail.com", "123456789", "nnnn");
        IUser user = new User();
        Answer ans = user.signup(demo);
        if (ans.getSuccess()) {
            System.out.println("success!!");
        }
        System.out.println(ans.getAns());
        ans = user.signin(demo);
        if (ans.getSuccess()) {
            System.out.println("success!!");
        }
        System.out.println(ans.getAns());
    }
}
