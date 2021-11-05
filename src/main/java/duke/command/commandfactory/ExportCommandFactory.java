package duke.command.commandfactory;


import java.nio.file.Path;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonWriter;
import duke.command.Command;
import duke.command.commandfactory.CommandFactory;
import duke.command.systemcommand.CommandExportTasksToFile;

public class ExportCommandFactory extends CommandFactory {

    public Command exportTasks(JsonArray tasksJson, Path writePath, JsonWriter jw) {
        return new CommandExportTasksToFile(tasksJson, writePath, jw);
    }
}
