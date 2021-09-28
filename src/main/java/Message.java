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
        System.out.println("    Task Added: " + s);
        System.out.println("_________________________________");
    }

    public static void msgMarkDone(TaskList myList, int taskNumber) {
        System.out.println("    Naisuuuu! This task is marked as done: ");
        System.out.println("    " + (taskNumber + 1) + ".[X] " + myList.getTaskDetail(taskNumber));
        System.out.println("_________________________________");
    }

    public static void msgList(TaskList myList) {
        for (int i = 0; i < myList.getNumOfItem(); i++) {
            if (!myList.getTaskDoneStatus(i)) {
                System.out.println("    " + Integer.toString(i + 1) + ".[ ] " + myList.getTaskDetail(i));
            } else {
                System.out.println("    " + Integer.toString(i + 1) + ".[X] " + myList.getTaskDetail(i));
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

        // ... \Duke\src\main\resources\buddha.txt
        // File.separator == " \ "

        String pathRoot = System.getProperty("user.dir");
        // pathRoot = D:\My Files\School Documents\Repository\Duke

        String pathRssFolder = "src" + File.separator + "main" + File.separator + "resources";
        // pathRssFolder = src\main\resources

        String pathFileName = "buddha.txt";
        // pathFileName = buddha.txt

        String filePath = pathRoot + File.separator + pathRssFolder + File.separator + pathFileName;
        // filePath = D:\My Files\School
        // Documents\Repository\Duke\src\main\resources\buddha.txt

        try {
            FileReader fr = new FileReader(filePath);
            // FileReader fr = new FileReader("\\My Files\\School
            // Documents\\Repository\\Duke\\src\\main\\resources\\buddha.txt");
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("\n >>> Buddah Protection is not in forced <<<\n");
            System.out.println(e);
        } finally {
            System.out.println(everything);
        }
    }
}
