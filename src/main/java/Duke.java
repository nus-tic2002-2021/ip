import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    static Scanner in = new Scanner(System.in);

    public static void ExtendTaskList() {
        while (true) {
            String input = in.nextLine();
            System.out.println(Ui.line);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(Ui.line);
                break;
            } else {
                Parser.parseInput(input);
            }
            System.out.println(Ui.line + "\n");
        }
    }

    public static void RunDuke() {
        try {
            File StorageFile = Storage.OpenStorageFile();
            Storage.ReadFileToArray(StorageFile);
        } catch (DukeException e) {
            e.printErrMsg();
        }
        Ui.StartDuke();
        Ui.Greet();
    }

    public static void main(String[] args) throws IOException {
        RunDuke();
        ExtendTaskList();
        try {
            Storage.writeListToFile(Storage.OpenStorageFile());
        } catch (DukeException e) {
            System.out.println("Failed to write to file");
        }
    }
}
