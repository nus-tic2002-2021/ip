package duke.dukeutility.config;


import java.io.File;

public class DukeIo {
    public static final String RESOURCE_PATH = System.getProperty("user.home") + File.separator + "duke";

    private static String pathStringDefaultTasksExportJsonPath = null;
    private static String pathStringDefaultTasksImportPath = null;

    public static String getDefaultTasksImportPathString() {
        if (DukeIo.pathStringDefaultTasksImportPath == null) {
            DukeIo.pathStringDefaultTasksImportPath = RESOURCE_PATH + File.separator + "tasks.json";
        }
        return DukeIo.pathStringDefaultTasksImportPath;

    }

    public static String getDefaultTasksExportPathString() {
        if (DukeIo.pathStringDefaultTasksExportJsonPath == null) {
            DukeIo.pathStringDefaultTasksExportJsonPath = RESOURCE_PATH + File.separator + "tasks.json";
        }
        return DukeIo.pathStringDefaultTasksExportJsonPath;
    }
}


