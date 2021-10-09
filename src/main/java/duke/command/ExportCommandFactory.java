package duke.command;


import com.google.gson.JsonArray;
import com.google.gson.stream.JsonWriter;
import duke.command.commandFactory.CommandFactory;
import duke.command.systemCommand.CommandExportTasksToFile;

public class ExportCommandFactory extends CommandFactory {

    public Command exportTasks(JsonArray tasksJson, JsonWriter jw) {
        return new CommandExportTasksToFile(tasksJson, jw);
    }
}
