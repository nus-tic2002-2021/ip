package Duke;

import Duke.Parser.*;
import Duke.Ui.Ui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Duke is a program that opens a storage file,
 * parses the tasks in the file into a list in Duke
 * and that allows users to do CRUD operations on tasks.
 */
public class Duke {

    static Scanner in = new Scanner(System.in);

    /**
     * Continuously read input from the user of Duke
     * and parse the different inputs.
     */
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

    /**
     * Starts Duke by opening storage file
     * and updating the duke list using th information from the file.
     * Then print the duke start up message and a greeting from the program.
     */
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

    /**
     * Runs the main logic of Duke program.
     * @param args
     * @throws IOException
     */
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
