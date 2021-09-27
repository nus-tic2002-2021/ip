import java.util.Scanner;
import java.io.*;

public class Duke {

    public static void main(String[] args) throws IOException {
        Message.msgGreet();

        boolean bye = false;

        String line;
        Scanner in = new Scanner(System.in);

        TaskList myList = new TaskList();

        while (!bye) {
            line = in.nextLine();
            if (line.equals("list")) {
                for (int i = 0; i < myList.getNumOfItem(); i++) {
                    System.out.println("    " + Integer.toString(i) + ": " + myList.getItem(i));
                }
                System.out.println("_________________________________");
            } else if (line.equals("bye")) {
                bye = true;
            } else {
                myList.addItem(line);
                Message.echo(line);
            }
        }

        in.close();
        Message.msgBye();
    }
}
