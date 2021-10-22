package duke.command;


import com.google.gson.JsonArray;
import com.google.gson.stream.JsonWriter;

import duke.command.commandfactory.CommandFactory;
import duke.command.systemcommand.CommandExportTasksToFile;

public class ExportCommandFactory extends CommandFactory {

    public Command exportTasks(JsonArray tasksJson, JsonWriter jw) {
        return new CommandExportTasksToFile(tasksJson, jw);
    }
}
