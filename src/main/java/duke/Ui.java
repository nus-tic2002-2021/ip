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
    private Boolean loop = true;

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
        return this.loop;
    }

    private void setLoop(Boolean p) {
        this.loop = p;
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

    public void printReadSuccess(String pathString) {
        this.getPrintStream().print("Success reading file " + pathString + System.lineSeparator());
    }

    public void printTerminateMessage() {
        this.getPrintStream().print("See you again!" + System.lineSeparator());
    }

    public void printEchoMessage(String text) {
        this.getPrintStream().print("Echoed after you: " + text + System.lineSeparator());
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

    private void printResponseTemplate(String text) {
        this.getPrintStream().print(text + System.lineSeparator());

    }

    private void printResponseAddedToDo(String desc, String id) {
        this.printResponseTemplate("Added To Do [id #" + id + "]: " + desc);
    }

    private void printResponseAddedDeadline(String desc, String id) {
        this.printResponseTemplate("Added Deadline [id #" + id + "]: " + desc);
    }

    private void printResponseAddedEvent(String desc, String id) {
        this.printResponseTemplate("Added Event [id #" + id + "]: " + desc);
    }

    private void printResponseInvalidCommand(String text) {
        this.printResponseTemplate(text);
    }

    private void printResponseUnknownRequest() {
        this.printResponseTemplate("Unknown command. . .");
    }


    private void printResponseTaskNotFound(String text) {
        this.printResponseTemplate("Task Not Found: " + text);
    }

    private void printResponseTaskDeleted(String id) {
        this.printResponseTemplate("Task Deleted: #" + id);
    }

    private void printResponseTaskRequestInvalidParameters(String text) {
        this.printResponseTemplate("Invalid parameters: " + text);
    }

    private void printResponseTaskFileSaved(String pathString) {
        this.printResponseTemplate("Saved task to file: " + pathString);
    }

    private void printResponseTaskReadPathInvalid() {
        this.printResponseTemplate("Read path not found/invalid. ");
    }

    protected void displayCommandResponse(Command c) throws Exception {
        ResponseType rt = c.getResponseType();
        if (rt == ResponseType.EXIT_LOOP) {
            this.setLoop(false);
            this.printExitLoop();
        } else if (rt == ResponseType.ECHO) {
            this.printEchoMessage(c.getArgs().get(1));
        } else if (rt == ResponseType.ERROR_COMMAND_EXECUTION) {
            this.printResponseTemplate(c.getArgs().get(2));
        } else if (rt == ResponseType.TASK_CREATE_TODO) {
            this.printResponseAddedToDo(c.getArgs().get(1), c.getArgs().get(2));
        } else if (rt == ResponseType.TASK_CREATE_DEADLINE) {
            this.printResponseAddedDeadline(c.getArgs().get(1), c.getArgs().get(2));
        } else if (rt == ResponseType.TASK_CREATE_EVENT) {
            this.printResponseAddedEvent(c.getArgs().get(1), c.getArgs().get(2));
        } else if (rt == ResponseType.TASK_LIST_ALL) {
            this.getPrintStream().print(c.getArgs().get(1));
        } else if (rt == ResponseType.TASK_LIST_FIND) {
            this.getPrintStream().print(
                "Query keyword in description: " + c.getArgs().get(2) + System.lineSeparator() + c.getArgs().get(1));
        } else if (rt == ResponseType.TASK_UPDATE_COMPLETE) {
            this.printResponseTemplate(String.join(" ", c.getArgs()));
        } else if (rt == ResponseType.ERROR_REQUEST_UNKNOWN) {
            this.printResponseUnknownRequest();
        } else if (rt == ResponseType.TASK_NOT_FOUND) {
            this.printResponseTaskNotFound(c.getArgs().get(1));
        } else if (rt == ResponseType.TASK_DELETE_TASK) {
            this.printResponseTaskDeleted(c.getArgs().get(1));
        } else if (rt == ResponseType.ERROR_REQUEST_INVALID_SYNTAX) {
            this.printResponseInvalidCommand(c.getArgs().get(1));
        } else if (rt == ResponseType.ERROR_REQUEST_INVALID_PARAMETERS) {
            this.printResponseTaskRequestInvalidParameters(c.getArgs().get(1));
        } else if (rt == ResponseType.FILE_SAVED) {
            this.printResponseTaskFileSaved(c.getArgs().get(1));
        } else if (rt == ResponseType.ERROR_INVALID_READ_FILE_PATH) {
            this.printResponseTaskReadPathInvalid();
        } else if (rt == ResponseType.FILE_READ) {
            this.printReadSuccess(c.getArgs().get(2));
        } else if (rt == ResponseType.TASK_UPDATE_INCOMPLETE) {
            this.printResponseTemplate(String.join(" ", c.getArgs()));
        } else if (rt == ResponseType.TASK_PROJECTION) {
            this.getPrintStream().print(
                "Tasks for the next " + c.getArgs().get(2) + " days: " + System.lineSeparator() + c.getArgs().get(1));
        } else if (rt == ResponseType.TASK_STATS_ALL) {
            this.getPrintStream().print(
                "Task Summary " + System.lineSeparator() + c.getArgs().get(1));
        } else if (rt == ResponseType.SCAN_DUPLICATE_DESCRIPTION) {
            this.getPrintStream().print(
                "Duplicates               \"[Description]\":[...ids] " + System.lineSeparator() + c.getArgs().get(1));
        } else {
            throw new Exception("Unhandled response type [" + rt + "].");
        }
    }

}
