package duke.command.commandfactory;

import static duke.dukeutility.parser.JsonTaskToObjectParser.jsonTaskToPojo;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.JsonObject;

import duke.TaskManager;
import duke.command.Command;
import duke.command.CommandJsonResponse;
import duke.command.errorcommand.CommandExecutionError;
import duke.command.errorcommand.CommandReadFileError;
import duke.command.errorcommand.CommandUnknownRequest;
import duke.command.systemcommand.CommandReadTasks;
import duke.command.taskcommand.taskimport.CommandImportDeadline;
import duke.command.taskcommand.taskimport.CommandImportEvent;
import duke.command.taskcommand.taskimport.CommandImportToDo;
import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.Task;
import duke.task.model.ToDo;

public class ImportCommandFactory extends CommandFactory {


    /**
     * Extract tasks from file.
     *
     * @param path file of saved tasks
     * @return command
     */
    public CommandJsonResponse executeExtractTasksFromFile(Path path) {
        Reader reader;
        try {
            reader = Files.newBufferedReader(path,
                StandardCharsets.UTF_8);
        } catch (Exception e) {
            return new CommandReadFileError("Invalid file read path. " + e);
        }
        return new CommandReadTasks(reader, path);
    }

    public Command executeImportJsonTask(JsonObject jsonObj, TaskManager taskManager) {
        Task task;
        try {
            task = jsonTaskToPojo(jsonObj);
        } catch (Exception e) {
            return new CommandExecutionError(e, "Error transforming json to java object " + jsonObj.toString());
        }
        if (task instanceof Event) {
            return new CommandImportEvent((Event) task, taskManager);
        } else if (task instanceof Deadline) {
            return new CommandImportDeadline((Deadline) task, taskManager);
        } else if (task instanceof ToDo) {
            return new CommandImportToDo((ToDo) task, taskManager);
        }
        return new CommandUnknownRequest("Unrecognised Task type.");
    }

}
