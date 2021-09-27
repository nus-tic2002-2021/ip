import java.io.*;

public class Message {

    public static void msgGreet() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
        System.out.println("_________________________________");
    }

    public static void msgError(Exception e) {
        System.out.println("Error Occurs: " + e);
    }

    public static void msgEcho(String s) {
        System.out.println("    added: " + s);
        System.out.println("_________________________________");
    }

    public static void msgList(TaskList myList) {
        for (int i = 0; i < myList.getNumOfItem(); i++) {
            if (!myList.getTaskDoneStatus(i)) {
                System.out.println("    " + Integer.toString(i) + ".[ ] " + myList.getTaskDetail(i));
            } else {
                System.out.println("    " + Integer.toString(i) + ".[X] " + myList.getTaskDetail(i));
            }
        }
        System.out.println("_________________________________");
    }

    public static void msgBye() throws IOException {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_________________________________");
        Message.msgBuddahProtectMe();
    }

    public static void msgBuddahProtectMe() throws IOException {
        String everything = "";
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/buddha.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } finally {
            System.out.println(everything);
        }
    }
}
