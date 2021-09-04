package duke.coronet.manager;

import duke.coronet.command.Command;
import duke.coronet.command.commandFactory.UxCommandFactory;
import duke.coronet.dukeUtility.enums.ResponseType;

import java.io.PrintStream;
import java.util.Scanner;


/**
* Manages input and output streams.
*/

public class UxManager {

    private Boolean _loop = true;
    private UxCommandFactory _uxCommandFactory = new UxCommandFactory(){
        @Override
        public Command executeTextCommand(String x, TaskManager xx, FileResourceManager xxx){
            return null;
        }
    };
    private PrintStream _out;
    public void setUxCommandFactory(UxCommandFactory _uxCommandFactory) {
        this._uxCommandFactory = _uxCommandFactory;
    }
    private UxCommandFactory getUxCommandFactory() {
        return this._uxCommandFactory;
    }

    private UxManager() {
    }

    public UxManager(PrintStream ps) {
        this.setPrintStream(ps);
    }

    private PrintStream getPrintStream() {
        return this._out;
    }
    public void setPrintStream(PrintStream ps) {
        this._out = ps;
    }

    public UxManager printEntryMessage() {
        String logo = " _                   _                                _             "
                + System.lineSeparator()
                + "| |                 | |                              | |                "
                + System.lineSeparator()
                + "| |_    __ _   ___  | | __  _ __ ___     __ _   ___  | |_    ___   _ __ "
                + System.lineSeparator()
                + "| __|  / _` | / __| | |/ / | '_ ` _ \\   / _` | / __| | __|  / _ \\ | '__|"
                + System.lineSeparator()
                + "| |_  | (_| | \\__ \\ |   <  | | | | | | | (_| | \\__ \\ | |_  |  __/ | |   "
                + System.lineSeparator()
                + " \\__|  \\__,_| |___/ |_|\\_\\ |_| |_| |_|  \\__,_| |___/  \\__|  \\___| |_|  "
                + System.lineSeparator();
        this.getPrintStream().print("Hello from" + System.lineSeparator() + logo);
        return this;
    }
    public void printBeginInputLoop() {
        this.getPrintStream().print("How can i help you? (See docs for usage)\n");
    }
    public void printExitLoopMessage() {
        this.getPrintStream().print("ok bye" + System.lineSeparator());
    }
    public void printTerminateMessage() {
        this.getPrintStream().print("See you again!" + System.lineSeparator());
    }
    public void printEchoMessage(String text) {
        this.getPrintStream().print("Echoed after you: " + text + System.lineSeparator());
    }
    /* request response cycle which takes in a line input and "renders" the command output */
    public void textCommandLoop(TaskManager taskManager, FileResourceManager frm) throws Exception {
        this.printBeginInputLoop();
        String textCommand;
        Scanner in = new Scanner(System.in);
        do {
            textCommand = in.nextLine();
            Command command = this.getUxCommandFactory().executeTextCommand(textCommand, taskManager, frm);
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
        }   else {
            throw new Exception("Unhandled response type [" + rt +  "].");
        }
    }
}
