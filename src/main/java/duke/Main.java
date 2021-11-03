package duke;

import static duke.dukeutility.config.DukeIo.getDefaultTasksExportPathString;
import static duke.dukeutility.config.DukeIo.getDefaultTasksImportPathString;
import java.io.PrintStream;

public class Main {
    /**
     * ENTRY CLASS
     *
     * @param args cli arguments
     */
    public static void main(String[] args) throws Exception {
        Main.run(System.out, new TaskManager(),
            new FileResourceManager(getDefaultTasksExportPathString(), getDefaultTasksImportPathString()));
    }

    /**
     * Helper to entry class. Allows custom parameters for test phase.
     *
     * @param out         out stream.
     * @param taskManager task manager to handle tasks.
     * @param frm         file resource manager
     * @throws Exception
     */
    public static void run(PrintStream out, TaskManager taskManager, FileResourceManager frm) throws Exception {
        Ui ui = new Ui(out);
        ui.printEntryMessage();
        frm.etlTasksFromJsonFileString(taskManager, ui);
        ui.textCommandLoop(taskManager, frm);
        ui.printTerminateMessage();
    }

}
