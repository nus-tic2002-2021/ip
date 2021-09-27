import java.util.Scanner;
import java.io.*;

public class Action {

    public static void StartDuke() {
        Message.msgGreet();
    }

    public static void RunDuke() {
        boolean bye = false;
        String line;
        Scanner in = new Scanner(System.in);
        TaskList myList = new TaskList();
        while (!bye) {
            line = in.nextLine();

            if (line.equals("list")) {
                Message.msgList(myList);
            } else if (line.equals("bye")) {
                bye = true;
            } else if (line.contains("done")) {
                // *** assume user key in done properly, tbc edit next time ***
                // System.out.println(line.substring(5));

                if (line.substring(0, 4).equals("done")) {
                    line.substring(5);
                }
            } else {
                myList.addItem(line);
                Message.msgEcho(line);
            }
        }
        in.close();
    }

    public static void EndDuke() {
        try {
            Message.msgBye();
        } catch (IOException error) {
            Message.msgError(error);
        }
    }
}
