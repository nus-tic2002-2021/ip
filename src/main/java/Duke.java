import app.Storage;
import task.TaskCommands;
import app.UI;

public class Duke {

    private Storage storage;
    private TaskCommands tasks;
    private UI ui;

    /** @param args to create program to run
     * Creating a new UI in main Duke & running
     */
    public static void main(String[] args) {
        UI ui = new UI();
        ui.run();
    }

}



