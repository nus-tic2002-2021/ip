package duke.testhelper.help.config;


import java.io.File;

public class DukeIoTestPath {

    private static final String resourceTestFolder =
        String.join(File.separator, new String[] {System.getProperty("user.home"), "duke", "test"});

    private static String testPathStringDefaultTasksExportJsonPath = null;
    private static String testPathStringDefaultTasksImportPath = null;

    public static String getDefaultTasksTestExportPathString() {
        if (DukeIoTestPath.testPathStringDefaultTasksExportJsonPath == null) {
            DukeIoTestPath.testPathStringDefaultTasksExportJsonPath =
                DukeIoTestPath.resourceTestFolder + File.separator + "exports" + File.separator + "tasks.json";
        }
        return DukeIoTestPath.testPathStringDefaultTasksExportJsonPath;
    }

    public static String getResourceTestFolder() {
        return DukeIoTestPath.resourceTestFolder;
    }

    public static String getDefaultTasksImportTestPathString() {
        if (DukeIoTestPath.testPathStringDefaultTasksImportPath == null) {
            DukeIoTestPath.testPathStringDefaultTasksImportPath =
                DukeIoTestPath.resourceTestFolder + File.separator + "imports" + File.separator + "tasks.json";
        }
        return DukeIoTestPath.testPathStringDefaultTasksImportPath;
    }


}
