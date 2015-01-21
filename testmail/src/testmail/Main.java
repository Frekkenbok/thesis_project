package testmail;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String testID = "1123123";

        File file = new File("files/out/java-persistence-developer-guide — копия.pdf");
        MailHandler.sendMail("ddmmyy20150112@gmail.com", "1123123", file);
    }
}
