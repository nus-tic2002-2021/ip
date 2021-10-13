package duke;

import duke.command.*;
import duke.exception.*;
import duke.parser.*;
import duke.storage.*;
import duke.tasklist.*;
import duke.ui.*;

//import java.nio.file.Files;
//import java.nio.file.Paths;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.ui.Printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.getTaskList());
        }
        catch (FileNotFoundException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e){
                    System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    // OLD CODE
    /*
    protected static Scanner input = new Scanner(System.in);
    protected static boolean isNotBye = true;
    protected static String cmd;
    */
    // OLD CODE

    public static void main(String[] args) {
        new Duke("src/main/java/duke/data/tasklist.csv").run();

        // OLD CODE
        /*
        Printer.printIntro();

        try {
            String filePath = "src/main/java/duke.data/tasks.txt";
            File f = new File(filePath);
            //Files.delete(Paths.get(filePath));

            FileWriter fw = new FileWriter(f);
            fw.write("");
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            //isNotBye = false;
        }
        catch (IndexOutOfBoundsException e) {
            //duke.ui.Printer.printInvalidFile();
            System.out.println("File not found");
            isNotBye = false;
        }
        while (isNotBye) {

            try {
                Printer.printHeader();
                cmd = input.nextLine();
                isNotBye = Parser.parse(cmd.toLowerCase());
            }

            catch (DukeException e) {
                Printer.printInvalidCmd();
            }

            catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
                //isNotBye = false;
            }

        }
        // OLD CODE
        */

    }

}
