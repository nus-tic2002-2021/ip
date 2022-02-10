import parser.Parser;
import storage.Storage;
import ui.UI;
import task.List;
import command.Command;

import error.DukeException;
import error.FileException;
import error.UnrecognizedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class Duke {

    private UI ui;
    private Parser Parser;
    private List tasks;
    private Storage storage;
    private boolean isExit = false;
    private static String Path = "data/tasks.txt";
    ;

    /**
     * Constructor for new Duke object using filePath given in main.
     * Creates a new ui.UI, parser.Parser and storage.Storage object.
     * Try to load a new List object from path.
     * If path does not exist, create a new empty List object
     *
     * @param filePath Path of file to be executed
     */

    public Duke(String filePath) {
        ui = new UI();
        Parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new List(storage.load());
            System.out.println("File loaded successfully.");
        } catch (FileException e) {
            System.out.println("File format is corrupted. Creating new file.");
            tasks = new List();
        } catch (Exception e) {
            System.out.println("Error reading file or does not exist. Creating new file.");
            tasks = new List();
        }
    }


    //javaFX
    public String getResponse(String input) {
        assert input.length() > 0;
        ByteArrayOutputStream outputMsg = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputMsg));
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, storage, ui);
            if (c.isExit()) {
                isExit = true;
            }
            return outputMsg.toString();

        } catch (UnrecognizedException e) {
            return ("Unrecognized Command");
        } catch (ArrayIndexOutOfBoundsException e) {
            return ("Please enter description after command");
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            return outputMsg.toString();
        }
    }

    public Boolean getIsExit() {
        return isExit;
    }

    /* Unused after JavaFX implementation
    public static void main(String[] args) {
        new Duke(Path).run();
    }
    */
    /**
     *
     *  Executes the program until isExit is true
     *
     */
    /* Unused after JavaFX implementation
    public void run(){
        ui.printIntro();

        while (!isExit){
            try{
                String fullCommand = ui.readCommand();
                ui.printLine();
                assert fullCommand.length() > 0;
                Command c = Parser.parse(fullCommand);
                c.execute(tasks,storage,ui);
                isExit = c.isExit();
            } catch (UnrecognizedException e){
                System.out.println("Unrecognized Command");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please enter description after command");
            } catch (DukeException e){
                ui.showError(e.getMessage());
            }
            finally {
                ui.printLine();
            }
        }

    }
     */
}
