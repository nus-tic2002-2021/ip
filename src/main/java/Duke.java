
//import java.nio.file.Files;
//import java.nio.file.Paths;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {

    /*
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        //...
    }
    */

    // OLD CODE
    protected static Scanner input = new Scanner(System.in);
    protected static boolean isNotBye = true;
    protected static String cmd;
    // OLD CODE

    public static void main(String[] args) {
        //new Duke("data/tasks.txt").run();


        // OLD CODE
        Printer.printIntro();

        try {
            String filePath = "src/main/java/data/tasks.txt";
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
        /*
        catch (IndexOutOfBoundsException e) {
            //Printer.printInvalidFile();
            System.out.println("File not found");
            isNotBye = false;
        }
        */
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

    }

}
