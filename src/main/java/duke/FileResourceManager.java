package duke;

import static duke.dukeutility.parser.PathParser.stringToPath;
import static duke.dukeutility.validator.TextCommandValidator.isParentDirectoryValid;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import duke.command.Command;
import duke.command.CommandJsonResponse;
import duke.command.commandfactory.ImportCommandFactory;
import duke.command.errorcommand.CommandExecutionError;
import duke.command.systemcommand.CommandExportTasksToFile;

public class FileResourceManager {

    private final ImportCommandFactory importCommandFactory = new ImportCommandFactory();

    private String exportPathString;
    private String importPathString;

    /**
     * Handles data imports and exports.
     *
     * @param exportPathString export path
     * @param importPathString import path
     */
    public FileResourceManager(String exportPathString, String importPathString) {
        this.setExportPathString(exportPathString);
        this.setImportPathString(importPathString);

    }

    private FileResourceManager() {
    }


    private ImportCommandFactory getImportCommandFactory() {
        return this.importCommandFactory;
    }

    private String getImportPathString() {
        return this.importPathString;
    }

    private void setImportPathString(String importPathString) {
        this.importPathString = importPathString;
    }

    public Path getImportPath() {
        return stringToPath(this.getImportPathString());
    }

    private String getExportPathString() {
        return this.exportPathString;
    }

    private void setExportPathString(String exportPathString) {
        this.exportPathString = exportPathString;
    }

    public Path getExportPath() {
        return stringToPath(this.getExportPathString());
    }



    /**
     * Save tasks in taskManager to this fileResourceManager's export path.
     *
     * @param taskManager task manager
     * @return
     */
    public Command executeSave(TaskManager taskManager) {
        JsonArray tasksJson = new JsonArray();
        Path exportPath;
        JsonWriter jw;

        // get the data to write to
        try {
            tasksJson = taskManager.getAllAsJson();
        } catch (Exception e) {
            return new CommandExecutionError(e, "Obtaining tasks collection from task manager failed " + tasksJson);
        }
        // try to get the export path
        try {
            exportPath = this.getExportPath();
            if (exportPath == null) {
                throw new Exception("Export path validation failed.");
            }
            Files.createDirectories(exportPath.getParent());
            if (!isParentDirectoryValid(exportPath)) {
                throw new Exception("Export path validation failed.");
            }
        } catch (Exception e) {
            return new CommandExecutionError(e, e.getMessage());
        }
        // create a Json writer of export path.
        try {
            jw = new JsonWriter(new FileWriter(exportPath.toString(), false));
        } catch (Exception e) {
            return new CommandExecutionError(e, "JsonWriter initialization failed.");
        }
        return new CommandExportTasksToFile(tasksJson, exportPath, jw);
    }

    /** Helper class
     * Import tasks from this import path.
     *
     * @return
     */
    public CommandJsonResponse executeExtractTasksFromFile() {
        Path path = this.getImportPath();
        return this.getImportCommandFactory().executeExtractTasksFromFile(path);
    }

    /** Helper class
     * Import tasks to task manager
     *
     * @param tasks       json array of tasks
     * @param taskManager task manager to import to.
     */
    public void importTasks(JsonArray tasks, TaskManager taskManager) {
        if (tasks == null || tasks.isEmpty()) {
            return;
        }
        for (JsonElement element : tasks) {
            if (element.isJsonObject()) {
                JsonObject jsonTask = element.getAsJsonObject();
                this.getImportCommandFactory().executeImportJsonTask(jsonTask, taskManager);
            }
        }
    }

    /**
     * Extract from import path, transform to json and load into task manager.
     * Display activities to user also.
     * @param taskManager task manager to import to
     * @param ui          ui display
     * @throws Exception
     */
    public void etlTasks(TaskManager taskManager, Ui ui) throws Exception {
        Path path = this.getImportPath();
        ui.printInitialLoadTaskAttempt(path);
        CommandJsonResponse reading = this.executeExtractTasksFromFile();
        ui.printCommandResponse(reading);
        JsonArray tasksFromFile = (JsonArray) reading.getJsonArg();
        this.importTasks(tasksFromFile, taskManager);
    }
}
