package duke;


import static duke.dukeutility.config.dukeIo.getDefaultTasksExportPathString;
import static duke.dukeutility.config.dukeIo.getDefaultTasksImportPathString;

import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws Exception {
        Main.run(System.out, new TaskManager(),
            new FileResourceManager(getDefaultTasksExportPathString(), getDefaultTasksImportPathString()));
    }

    public static void run(PrintStream out, TaskManager taskManager, FileResourceManager frm) throws Exception {
        Ui ui = new Ui(out);
        ui.printEntryMessage();
        frm.etlTasksFromJsonFileString(taskManager, ui);
        ui.textCommandLoop(taskManager, frm);
        ui.printTerminateMessage();
    }

}
