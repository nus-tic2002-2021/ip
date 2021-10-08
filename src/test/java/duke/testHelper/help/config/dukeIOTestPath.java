package duke.testHelper.help.config;


import java.io.File;

public class dukeIOTestPath {
    public static String resourceTestFolder = System.getProperty("user.home") + File.separator + "duke" + File.separator + "test" ;
    private static  String TEST_PATH_STRING_DEFAULT_TASKS_EXPORT_JSON_PATH = null;

    public static  String getDefaultTasksTestExportPathString() {
        if (dukeIOTestPath.TEST_PATH_STRING_DEFAULT_TASKS_EXPORT_JSON_PATH == null) {
            dukeIOTestPath.TEST_PATH_STRING_DEFAULT_TASKS_EXPORT_JSON_PATH = dukeIOTestPath.resourceTestFolder + File.separator + "exports" + File.separator + "tasks.json";
        }
        return dukeIOTestPath.TEST_PATH_STRING_DEFAULT_TASKS_EXPORT_JSON_PATH;
    }
}
