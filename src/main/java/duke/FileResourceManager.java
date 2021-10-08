package duke;

import com.google.gson.JsonArray;
import com.google.gson.stream.JsonWriter;
import duke.command.Command;
import duke.command.ExportCommandFactory;
import duke.command.errorCommand.CommandExecutionError;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static duke.dukeUtility.validator.TextCommandValidator.isParentDirectoryValid;

public class FileResourceManager {

    private final ExportCommandFactory _exportCommandFactory = new ExportCommandFactory();
    private String exportPathString;
    private ExportCommandFactory getExportCommandFactory() {
        return this._exportCommandFactory;
    }
    public FileResourceManager(String exportPathString, String importPathString) {
        this.setExportPathString(exportPathString);
    }
    private FileResourceManager() {
    }

    public Command executeExportTasks(JsonArray tasksJson, JsonWriter jw) {
        return this.getExportCommandFactory().exportTasks(tasksJson, jw);
    }
    private String getExportPathString() {
        return this.exportPathString;
    }
    private void setExportPathString(String exportPathString) {
        this.exportPathString = exportPathString;
    }
    public Path getExportPath()  {
        try {
            return Paths.get(this.getExportPathString());
        } catch (Exception e) {
            return null;
        }
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
}
