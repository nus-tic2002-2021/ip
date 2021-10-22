package duke.testhelper.help.config;


import java.io.File;

public class DukeIoTestPath {

    private static String RESOURCE_TEST_FOLDER =
        String.join(File.separator, new String[] {System.getProperty("user.home"), "duke", "test"});

    private static String TEST_PATH_STRING_DEFAULT_TASKS_EXPORT_JSON_PATH = null;
    private static String TEST_PATH_STRING_DEFAULT_TASKS_IMPORT_PATH = null;

    public static String getDefaultTasksTestExportPathString() {
        if (DukeIoTestPath.TEST_PATH_STRING_DEFAULT_TASKS_EXPORT_JSON_PATH == null) {
            DukeIoTestPath.TEST_PATH_STRING_DEFAULT_TASKS_EXPORT_JSON_PATH =
                DukeIoTestPath.RESOURCE_TEST_FOLDER + File.separator + "exports" + File.separator + "tasks.json";
        }
        return DukeIoTestPath.TEST_PATH_STRING_DEFAULT_TASKS_EXPORT_JSON_PATH;
    }

    public static String getResourceTestFolder() {
        return DukeIoTestPath.RESOURCE_TEST_FOLDER;
    }

    public static String getDefaultTasksImportTestPathString() {
        if (DukeIoTestPath.TEST_PATH_STRING_DEFAULT_TASKS_IMPORT_PATH == null) {
            DukeIoTestPath.TEST_PATH_STRING_DEFAULT_TASKS_IMPORT_PATH =
                DukeIoTestPath.RESOURCE_TEST_FOLDER + File.separator + "imports" + File.separator + "tasks.json";
        }
        return DukeIoTestPath.TEST_PATH_STRING_DEFAULT_TASKS_IMPORT_PATH;
    }


}
