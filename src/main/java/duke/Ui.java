package duke;


import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Scanner;
import duke.command.Command;
import duke.command.commandfactory.UiCommandFactory;
import duke.dukeutility.enums.ResponseType;


/**
 * Handles terminal display.
 */
public class Ui {
    private final UiCommandFactory uiCommandFactory = new UiCommandFactory();
    private PrintStream out;
    private Boolean isLoop = true;

    private Ui() {
    }

    public Ui(PrintStream ps) {
        this.setPrintStream(ps);
    }

    private PrintStream getPrintStream() {
        return this.out;
    }

    public void setPrintStream(PrintStream ps) {
        this.out = ps;
    }

    private Boolean isLoop() {
        return this.isLoop;
    }

    private void setIsLoop(Boolean p) {
        this.isLoop = p;
    }

    private UiCommandFactory getUiCommandFactory() {
        return this.uiCommandFactory;
    }

    /**
     * Welcome Message
     */
    public void printEntryMessage() {
        String logo = " _                   _                                _             " + System.lineSeparator()
            + "| |                 | |                              | |                " + System.lineSeparator()
            + "| |_    __ _   ___  | | __  _ __ ___     __ _   ___  | |_    ___   _ __ " + System.lineSeparator()
            + "| __|  / _` | / __| | |/ / | '_ ` _ \\   / _` | / __| | __|  / _ \\ | '__|" + System.lineSeparator()
            + "| |_  | (_| | \\__ \\ |   <  | | | | | | | (_| | \\__ \\ | |_  |  __/ | |   " + System.lineSeparator()
            + " \\__|  \\__,_| |___/ |_|\\_\\ |_| |_| |_|  \\__,_| |___/  \\__|  \\___| |_|  " + System.lineSeparator();
        this.getPrintStream().print("Hello from" + System.lineSeparator() + logo);
    }

    public void printBeginInputLoop() {
        this.getPrintStream().print("How can i help you? (See docs for usage)\n");
    }

    public String getExitLoopMessage() {
        return "ok bye" + System.lineSeparator();
    }

    /**
     * Message: Attempt to load task.
     *
     * @param path
     */
    public void printInitialLoadTaskAttempt(Path path) {
        if (path == null) {
            this.getPrintStream().print("Import path empty. " + System.lineSeparator());
        } else {
            this.getPrintStream().print("Attempting to import tasks from " + path + "." + System.lineSeparator());
        }
    }

    public void printTerminateMessage() {
        this.getPrintStream().print("See you again!" + System.lineSeparator());
    }

    public void printEndOfResponse() {
        this.getPrintStream().print("\t\t\t\t\t\t\t\t -" + System.lineSeparator());
    }

    /**
     * cli session with request-response cycle
     *
     * @param taskManager
     * @param frm
     * @throws Exception
     */
    public void textCommandLoop(TaskManager taskManager, FileResourceManager frm) throws Exception {
        this.printBeginInputLoop();
        String textCommand;
        Scanner in = new Scanner(System.in);
        do {
            textCommand = in.nextLine();
            Command command = this.getUiCommandFactory().executeTextCommand(textCommand, taskManager, frm);
            this.displayCommandResponse(command);
            this.printEndOfResponse();
        } while (this.isLoop());
    }

    protected void displayCommandResponse(Command c) throws Exception {
        ResponseType rt = c.getResponseType();

        String output = "";

        switch (rt) {
        case EXIT_LOOP:
            this.setIsLoop(false);
            output = (this.getExitLoopMessage());
            break;
        case TASK_LIST_ALL:
        case TASK_LIST_FIND:
        case TASK_PROJECTION:
        case TASK_STATS_ALL:
        case SCAN_DUPLICATE_DESCRIPTION:
        case TASK_UPDATE_COMPLETE:
        case TASK_UPDATE_INCOMPLETE:
        case TASK_NOT_FOUND:
        case TASK_DELETE_TASK:
        case TASK_CREATE_TODO:
        case TASK_CREATE_DEADLINE:
        case TASK_CREATE_EVENT:
        case ERROR_COMMAND_EXECUTION:
        case ERROR_REQUEST_UNKNOWN:
        case ERROR_REQUEST_INVALID_SYNTAX:
        case ERROR_REQUEST_INVALID_PARAMETERS:
        case ERROR_INVALID_READ_FILE_PATH:
        case FILE_SAVED:
        case FILE_READ:
            output = c.getResponse();
            break;
        default:
            throw new Exception("Unhandled response type [" + rt + "].");
        }
        this.getPrintStream().print(output);
    }
}
