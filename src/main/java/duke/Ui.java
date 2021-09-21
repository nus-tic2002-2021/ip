package duke;


import java.io.PrintStream;


/**
 * Handles terminal display.
 */
public class Ui {

    private PrintStream _out;

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

    public void printTerminateMessage() {
        this.getPrintStream().print("See you again!\n");
    }
}
