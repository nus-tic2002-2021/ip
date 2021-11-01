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

    private static String addLine(String text) {
        return text + System.lineSeparator();
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

    public void printExitLoop() {
        this.getPrintStream().print("ok bye" + System.lineSeparator());
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


    public String getReadSuccess(String pathString) {
        return ("Success reading file " + pathString + System.lineSeparator());
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

    private String getResponseTaskRequestInvalidParameters(String text) {
        return addLine("Invalid parameters: " + text);
    }

    private String getResponseTaskFileSaved(String pathString) {
        return addLine("Saved task to file: " + pathString);
    }

    protected void displayCommandResponse(Command c) throws Exception {
        ResponseType rt = c.getResponseType();
        if (rt == ResponseType.EXIT_LOOP) {
            this.setIsLoop(false);
            this.printExitLoop();
        } else if (rt == ResponseType.TASK_LIST_ALL) {
            this.getPrintStream().print(c.getResponse());
        } else if (rt == ResponseType.TASK_LIST_FIND) {
            this.getPrintStream().print(c.getResponse());
        } else if (rt == ResponseType.TASK_PROJECTION) {
            this.getPrintStream().print(c.getResponse());
        } else if (rt == ResponseType.TASK_STATS_ALL) {
            this.getPrintStream().print(c.getResponse());
        } else if (rt == ResponseType.TASK_UPDATE_COMPLETE) {
            String output = addLine(c.getResponse());
            this.getPrintStream().print(output);
        } else if (rt == ResponseType.TASK_UPDATE_INCOMPLETE) {
            String output = addLine(c.getResponse());
            this.getPrintStream().print(output);
        } else if (rt == ResponseType.TASK_NOT_FOUND) {
            String output = addLine(c.getResponse());
            this.getPrintStream().print(output);
        } else if (rt == ResponseType.TASK_DELETE_TASK) {
            String output = addLine(c.getResponse());
            this.getPrintStream().print(output);
        } else if (rt == ResponseType.TASK_CREATE_TODO) {
            String output = addLine(c.getResponse());
            this.getPrintStream().print(output);
        } else if (rt == ResponseType.TASK_CREATE_DEADLINE) {
            String output = addLine(c.getResponse());
            this.getPrintStream().print(output);
        } else if (rt == ResponseType.TASK_CREATE_EVENT) {
            String output = addLine(c.getResponse());
            this.getPrintStream().print(output);
        } else if (rt == ResponseType.SCAN_DUPLICATE_DESCRIPTION) {
            this.getPrintStream().print(c.getResponse());
        } else if (rt == ResponseType.ERROR_COMMAND_EXECUTION) {
            String output = addLine(c.getResponse());
            this.getPrintStream().print(output);
        } else if (rt == ResponseType.ERROR_REQUEST_UNKNOWN) {
            String output = addLine(c.getResponse());
            this.getPrintStream().print(output);
        } else if (rt == ResponseType.ERROR_REQUEST_INVALID_SYNTAX) {
            String output = addLine(c.getResponse());
            this.getPrintStream().print(output);
        } else if (rt == ResponseType.ERROR_REQUEST_INVALID_PARAMETERS) {
            String output = this.getResponseTaskRequestInvalidParameters(c.getResponse());
            this.getPrintStream().print(output);
        } else if (rt == ResponseType.ERROR_INVALID_READ_FILE_PATH) {
            String output = addLine(c.getResponse());
            this.getPrintStream().print(output);
        } else if (rt == ResponseType.FILE_SAVED) {
            String output = this.getResponseTaskFileSaved(c.getResponse());
            this.getPrintStream().print(output);
        } else if (rt == ResponseType.FILE_READ) {
            String output = this.getReadSuccess(c.getResponse());
            this.getPrintStream().print(output);
        } else {
            throw new Exception("Unhandled response type [" + rt + "].");
        }
    }

}
