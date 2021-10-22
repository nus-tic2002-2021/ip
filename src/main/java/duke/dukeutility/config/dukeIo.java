package duke.dukeutility.config;


import java.io.File;

public class dukeIo {
    public static final String RESOURCE_PATH = System.getProperty("user.home") + File.separator + "duke";

    private static String pathStringDefaultTasksExportJsonPath = null;
    private static String pathStringDefaultTasksImportPath = null;

    public static String getDefaultTasksImportPathString() {
        if (dukeIo.pathStringDefaultTasksImportPath == null) {
            dukeIo.pathStringDefaultTasksImportPath = RESOURCE_PATH + File.separator + "tasks.json";
        }
        return dukeIo.pathStringDefaultTasksImportPath;
    }

    public static String getDefaultTasksExportPathString() {
        if (dukeIo.pathStringDefaultTasksExportJsonPath == null) {
            dukeIo.pathStringDefaultTasksExportJsonPath = RESOURCE_PATH + File.separator + "tasks.json";
        }
        return dukeIo.pathStringDefaultTasksExportJsonPath;
    }

}
