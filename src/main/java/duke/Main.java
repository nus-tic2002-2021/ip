package duke;

import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws Exception {
        Main.run(System.out, new TaskManager());
    }

    public static void run(PrintStream out, TaskManager taskManager) throws Exception {
        Ui ui = new Ui(out);
        ui.printEntryMessage();
        ui.textCommandLoop(taskManager);
        ui.printTerminateMessage();
    }
}
