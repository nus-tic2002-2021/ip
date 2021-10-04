import javax.swing.text.html.parser.Parser;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
public class Duke {
    UserInterface ui;
    TaskList taskList;
    Storage storage;

    public Duke() {
        this.ui = new UserInterface();
        this.taskList = new TaskList();
    }

    public void run() {
        ui.logo();
        ui.showWelcomeMessage();
    }

    public void userCommand() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getInput();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();

    }

}
