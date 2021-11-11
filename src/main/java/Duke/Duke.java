package Duke;

import Duke.DukeLogic.DukeException;
import Duke.DukeLogic.Storage;
import Duke.Parser.*;
import Duke.DukeLogic.Ui;
import java.io.File;
import java.util.Scanner;

/**
 * Duke is a program that opens a storage file,
 * parses the tasks in the file into a list in Duke
 * and that allows users to do CRUD operations on tasks.
 */
public class Duke {
    static Scanner in = new Scanner(System.in);

    /**
     * Scans and returns user input as String.
     * @return
     */
    public static String getInput() {
        String input = in.nextLine();
        return input;
    }

    /**
     * Starts Duke by opening storage file
     * and updating the duke list using th information from the file.
     * Then print the duke start up message and a greeting from the program.
     */
    public static void runDuke() {
        try {
            File StorageFile = Storage.openStorageFile();
            Storage.readFileToArray(StorageFile);
        } catch (DukeException e) {
            e.printErrMsg();
        }
        Ui.startDuke();
        Ui.greet();
    }

    /**
     * Continuously read input from the user of Duke
     * and parse the different inputs.
     */
    public static void useDuke() {
        while (true) {
            String input = getInput();
            System.out.println(Ui.LINE);
            if (input.equals("bye")) {
                endDuke();
                break;
            } else {
                InputParser.parseInput(input);
            }
            System.out.println(Ui.LINE + "\n");
        }
    }

    public static void endDuke() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Ui.LINE);
    }

    /**
     * Runs the main logic of Duke program.
     * @param args
     */
    public static void main(String[] args) {
        runDuke();
        useDuke();
        try {
            Storage.writeListToFile(Storage.openStorageFile());
        } catch (DukeException e) {
            System.out.println("Failed to write to file");
        }
    }
}
