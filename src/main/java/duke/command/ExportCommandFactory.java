package duke.command;


import java.nio.file.Path;
import com.google.gson.JsonArray;
import duke.command.commandfactory.CommandFactory;
import duke.command.systemcommand.CommandExportTasksToFile;

public class ExportCommandFactory extends CommandFactory {

    public Command exportTasks(JsonArray tasksJson, Path writePath) {
        return new CommandExportTasksToFile(tasksJson, writePath);
    }
}
