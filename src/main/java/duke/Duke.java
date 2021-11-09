package duke;

import duke.action.ParserFacade;
import duke.storage.FileAccess;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

/**
 * Begin the program here.
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */
public class Duke {

    private FileAccess fileAccess;
    private TaskList myList;
    private Ui ui;

    public Duke(String filepath){
        ui = new Ui();
        fileAccess = new FileAccess(filepath);
        // todo try load task here
    }

    public void initialize(){
        StartDuke.run();
        RunDuke.run(myList, ui);
        EndDuke.run();
    }

    /**
     * Starting point of the program
     */
    public static void main(String[] args) {
        Duke duke = new Duke("src\\resources\\progress.txt");
        duke.initialize();
    }
}
