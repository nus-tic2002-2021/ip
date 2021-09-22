package duke;


import duke.command.Command;
import duke.command.commandFactory.UiCommandFactory;
import duke.dukeUtility.enums.ResponseType;

import java.io.PrintStream;
import java.util.Scanner;


/**
 * Handles terminal display.
 */
public class Ui {

    private PrintStream _out;
    private Boolean _loop = true;
    private UiCommandFactory _UiCommandFactory = new UiCommandFactory() {
        @Override
        public Command executeTextCommand(String text, TaskManager __) {
            return null;
        }
    };

    private Ui() {
    }

    public Ui(PrintStream ps) {
        this.setPrintStream(ps);
    }

    private PrintStream getPrintStream() {
        return this._out;
    }

    public void setPrintStream(PrintStream ps) {
        this._out = ps;
    }

    public Ui printEntryMessage() {
        String logo = " _                   _                                _             \n"
                + "| |                 | |                              | |                \n"
                + "| |_    __ _   ___  | | __  _ __ ___     __ _   ___  | |_    ___   _ __ \n"
                + "| __|  / _` | / __| | |/ / | '_ ` _ \\   / _` | / __| | __|  / _ \\ | '__|\n"
                + "| |_  | (_| | \\__ \\ |   <  | | | | | | | (_| | \\__ \\ | |_  |  __/ | |   \n"
                + " \\__|  \\__,_| |___/ |_|\\_\\ |_| |_| |_|  \\__,_| |___/  \\__|  \\___| |_|  \n";
        this.getPrintStream().print("Hello from\n" + logo);
        return this;
    }

    public void printBeginInputLoop() {
        this.getPrintStream().print("How can i help you? (See docs for usage)\n");
    }

    public void printExitLoopMessage() {
        this.getPrintStream().print("ok bye" + System.lineSeparator());
    }

    public void printEchoMessage(String text) {
        this.getPrintStream().print("Echoed after you: " + text + System.lineSeparator());
    }

    private UiCommandFactory getUiCommandFactory() {
        return this._UiCommandFactory;
    }

    public void setUiCommandFactory(UiCommandFactory _UiCommandFactory) {
        this._UiCommandFactory = _UiCommandFactory;
    }

    public void textCommandLoop(TaskManager taskManager) throws Exception {
        this.printBeginInputLoop();
        String textCommand;
        Scanner in = new Scanner(System.in);
        do {
            textCommand = in.nextLine();
            Command command = this.getUiCommandFactory().executeTextCommand(textCommand, taskManager);
            this.displayCommandResponse(command);
            this.getPrintStream().print("\t\t\t\t\t\t\t\t -" + System.lineSeparator());
        } while (this._loop);
    }

    protected void displayCommandResponse(Command c) throws Exception {
        ResponseType rt = c.getResponseType();
        if (rt == ResponseType.EXIT_LOOP) {
            this._loop = false;
            this.printExitLoopMessage();
        } else if (rt == ResponseType.ECHO) {
            this.printEchoMessage(c.getArgs().get(1));
        } else if (rt == ResponseType.ERROR_COMMAND_EXECUTION) {
            this.getPrintStream().print(c.getArgs().get(2));
        } else if (rt == ResponseType.TASK_CREATE_TODO) {
            this.getPrintStream().print("Added To Do: " + c.getArgs().get(2) + System.lineSeparator());
        } else if (rt == ResponseType.TASK_LIST_ALL) {
            this.getPrintStream().print(c.getArgs().get(1));
        } else if (rt == ResponseType.TASK_UPDATE_DONE_STATUS) {
            this.getPrintStream().print(String.join(" ", c.getArgs()) + System.lineSeparator());
        } else {
            throw new Exception("Unhandled response type [" + rt + "].");
        }
    }

    public void printTerminateMessage() {
        this.getPrintStream().print("See you again!\n");
    }
}
