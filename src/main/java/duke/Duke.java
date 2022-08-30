package duke;

import duke.exception.UnableToLoadProcessException;
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

    /**
     * Constructor
     * <p>
     * initialize the ui, fileAccess and taskList
     * load the progress into taskList
     */
    public Duke(String filepath) {
        ui = new Ui();
        fileAccess = new FileAccess(filepath);
        myList = new TaskList();

        try {
            String progress = fileAccess.loadProgressFromFile();
            new LoadDuke(myList, progress).run();
        } catch (UnableToLoadProcessException e) {
            Message.msgUnableToLoadProgress();
        }
    }

    /**
     * Start the Duke program
     */
    public void initialize() {
        StartDuke.run();
        new RunDuke(myList, ui, fileAccess).run();
    }

    /**
     * End the Duke program
     */
    public void end() {
        new EndDuke(fileAccess).run();
    }

    /**
     * Starting point of the program
     */
    public static void main(String[] args) {
        Duke duke = new Duke("src\\resources\\progress.txt");
        duke.initialize();
        duke.end();
    }
}
