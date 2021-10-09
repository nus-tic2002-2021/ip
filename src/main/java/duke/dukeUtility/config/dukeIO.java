package duke.dukeUtility.config;


import java.io.File;

public class dukeIO {


    public static final String resourcePath = System.getProperty("user.home") + File.separator + "duke" ;
    private static String PATH_STRING_DEFAULT_TASKS_IMPORT_PATH = null;
    public static  String PATH_STRING_DEFAULT_TASKS_EXPORT_JSON_PATH = null;
    public static  String getDefaultTasksImportPathString() {
        if (dukeIO.PATH_STRING_DEFAULT_TASKS_IMPORT_PATH == null) {
            dukeIO.PATH_STRING_DEFAULT_TASKS_IMPORT_PATH = resourcePath + File.separator + "tasks.json";
        }
        return dukeIO.PATH_STRING_DEFAULT_TASKS_IMPORT_PATH;
    }

    public static  String getDefaultTasksExportPathString() {
        if (dukeIO.PATH_STRING_DEFAULT_TASKS_EXPORT_JSON_PATH == null) {
            dukeIO.PATH_STRING_DEFAULT_TASKS_EXPORT_JSON_PATH = resourcePath + File.separator + "tasks.json";
        }
        return dukeIO.PATH_STRING_DEFAULT_TASKS_EXPORT_JSON_PATH;
    }

}
