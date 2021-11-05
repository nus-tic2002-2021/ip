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
import duke.command.ExportCommandFactory;
import duke.command.commandfactory.ImportCommandFactory;
import duke.command.errorcommand.CommandExecutionError;

public class FileResourceManager {

    private final ExportCommandFactory exportCommandFactory = new ExportCommandFactory();
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

    private ExportCommandFactory getExportCommandFactory() {
        return this.exportCommandFactory;
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

    /**
     * Writes tasks to path.
     *
     * @param tasksJson tasks in JSON format
     * @return
     */
    public Command executeExportTasks(JsonArray tasksJson, Path writePath) {
        return this.getExportCommandFactory().exportTasks(tasksJson, writePath);
    }

    /**
     * Import tasks from this import path.
     *
     * @return
     */
    public CommandJsonResponse executeExtractTasksFromFile() {
        Path path = this.getImportPath();
        return this.getImportCommandFactory().executeExtractTasksFromFile(path);
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
    public Command executeCommandSave(TaskManager taskManager) {
        JsonWriter jw;
        JsonArray tasksJson = new JsonArray();
        Path exportPath;
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
        try {
            jw = new JsonWriter(new FileWriter(exportPath.toString(), false));
        } catch (Exception e) {
            return new CommandExecutionError(e, "JsonWriter initialization failed.");
        }
        try {
            tasksJson = taskManager.getAllAsJson();
        } catch (Exception e) {
            return new CommandExecutionError(e, "Obtaining tasks collection from task manager failed " + tasksJson);
        }
        return this.executeExportTasks(tasksJson, exportPath);
    }

    /**
     * Import tasks to task manager
     *
     * @param tasks       json array of tasks
     * @param taskManager task manager to import to.
     */
    public void importTasksJson(JsonArray tasks, TaskManager taskManager) {
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
     *
     * @param taskManager task manager to import to
     * @param ui          ui display
     * @throws Exception
     */
    public void etlTasksFromJsonFileString(TaskManager taskManager, Ui ui) throws Exception {
        Path path = this.getImportPath();
        ui.printInitialLoadTaskAttempt(path);
        CommandJsonResponse reading = this.getImportCommandFactory().executeExtractTasksFromFile(path);
        ui.printCommandResponse(reading);
        JsonArray tasksFromFile = (JsonArray) reading.getJsonArg();
        this.importTasksJson(tasksFromFile, taskManager);
    }


}
