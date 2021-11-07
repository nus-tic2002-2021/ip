package duke;

import static duke.dukeutility.config.DukeIo.getDefaultTasksExportPathString;
import static duke.dukeutility.config.DukeIo.getDefaultTasksImportPathString;

import java.io.PrintStream;

public class Main {
    /**
     * @see Main#run(PrintStream, TaskManager, FileResourceManager)
     */
    public static void main(String[] args) throws Exception {
        Main.run(System.out, new TaskManager(),
            new FileResourceManager(getDefaultTasksExportPathString(), getDefaultTasksImportPathString()));
    }

    /**
     * Helper to entry class. This is the default routine for the program.
     * Allows custom PrintStream, TaskManager and FileResourceManager for test phase.
     *
     * @param out         out stream.
     * @param taskManager task manager to handle tasks.
     * @param frm         file resource manager
     * @throws Exception
     */
    public static void run(PrintStream out, TaskManager taskManager, FileResourceManager frm) throws Exception {
        // create a Ui to handle cli I/O.
        Ui ui = new Ui(out);

        ui.printEntryMessage();
        // loads tasks
        frm.etlTasks(taskManager, ui);
        // commence request response cycle
        ui.runTextCommandLoop(taskManager, frm);

        ui.printTerminateMessage();
    }

}
