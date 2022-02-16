import Common.Message;
import data.Commands;
import functions.ListTask;
import storage.Storage;
import ui.Ui;

import static ui.Ui.BUFFER;

import java.io.IOException;
import java.nio.file.InvalidPathException;

public class Duke {
    private Storage storage;
    private Ui ui;
    private ListTask newlist = new ListTask();
    public Duke(String filePath) {
        ui = new Ui();
        try{
            storage = new Storage(filePath);
            newlist.init();
        }catch (InvalidPathException p){
            System.out.println(BUFFER+p.getMessage() + Message.showFilePathErrorMessage());
        }catch (IOException o){
            System.out.println(BUFFER+o.getMessage() + Message.showFileIOErrorMessage());
        }

    }

    public void run() {
        ui.showWelcomeMessage();
        String userInput = ui.readUserInput();  // Read user input
        while(!userInput.trim().equalsIgnoreCase(Commands.BYE.toString())){
            newlist.addTask(userInput, storage);
            userInput = ui.readUserInput();
        }
        ui.showValedicMessage();
    }

    public static void main(String[] args)
    {
        (new Duke("data/userdata.txt")).run();
    }

}
