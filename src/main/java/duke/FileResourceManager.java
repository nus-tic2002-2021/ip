package duke;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import duke.command.Command;
import duke.command.CommandJsonResponse;
import duke.command.ExportCommandFactory;
import duke.command.commandfactory.FileCommandFactory;
import duke.command.commandfactory.ImportCommandFactory;
import duke.command.errorcommand.CommandExecutionError;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static duke.dukeUtility.parser.PathParser.stringToPath;
import static duke.dukeUtility.validator.TextCommandValidator.isParentDirectoryValid;

public class FileResourceManager {

    private final ExportCommandFactory _exportCommandFactory = new ExportCommandFactory();
    private final ImportCommandFactory _importCommandFactory = new ImportCommandFactory();
    private final FileCommandFactory _fileCommandFactory = new FileCommandFactory();

    private String exportPathString;
    private String importPathString;

    private ExportCommandFactory getExportCommandFactory() {
        return this._exportCommandFactory;
    }
    public FileResourceManager(String exportPathString, String importPathString) {
        this.setExportPathString(exportPathString);
        this.setImportPathString(importPathString);

    }
    private FileResourceManager() {
    }
    private String getImportPathString() {
        return this.importPathString;
    }

    public Command executeExportTasks(JsonArray tasksJson, JsonWriter jw) {
        return this.getExportCommandFactory().exportTasks(tasksJson, jw);
    }
    public CommandJsonResponse executeExtractTasksFromFile(){
        Path path = this.getImportPath();
        return this.getFileCommandFactory().executeExtractTasksFromFile(path);
    }
    private FileCommandFactory getFileCommandFactory() {
        return this._fileCommandFactory;
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
    public Path getExportPath()  {
        return stringToPath(this.getExportPathString());
    }
    private void setImportPathString(String importPathString) {
        this.importPathString = importPathString;
    }
    public Command executeCommandSave(TaskManager taskManager) {
        JsonWriter jw;
        JsonArray tasksJson = new JsonArray();
        Path exportPath;
        try {
            exportPath = this.getExportPath();
            if(exportPath == null){
                throw new Exception("Export path validation failed.");
            }
            Files.createDirectories(exportPath.getParent());
            if (!isParentDirectoryValid(exportPath)) {
                throw new Exception("Export path validation failed.");
            }
        } catch (Exception e) {
            return new CommandExecutionError(e,e.getMessage() );
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
        return this.executeExportTasks(tasksJson, jw);
    }

    public void importTasksJson(JsonArray array, TaskManager taskManager) {
        if (array == null || array.isEmpty()) {
            return;
        }
        for (JsonElement element : array) {
            if (element.isJsonObject()) {
                JsonObject jsonTask = element.getAsJsonObject();
                this.getImportCommandFactory().executeImportJsonTask(jsonTask, taskManager);
            }
        }
    }

    public void etlTasksFromJsonFileString(TaskManager taskManager, Ui ui) throws Exception {
        Path path = this.getImportPath();
        ui.printInitialLoadTaskAttempt(path);
        CommandJsonResponse reading = this.getFileCommandFactory().executeExtractTasksFromFile(path);
        ui.displayCommandResponse(reading);
        JsonArray tasksFromFile = (JsonArray) reading.getJsonArg();
        this.importTasksJson(tasksFromFile, taskManager);
    }

    private ImportCommandFactory getImportCommandFactory() {
        return this._importCommandFactory;
    }

}
