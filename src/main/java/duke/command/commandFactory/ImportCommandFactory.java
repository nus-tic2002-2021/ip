package duke.command.commandFactory;

import com.google.gson.JsonObject;
import duke.TaskManager;
import duke.command.Command;
import duke.command.errorCommand.CommandExecutionError;
import duke.command.errorCommand.CommandUnknownRequest;
import duke.command.taskCommand.taskImport.CommandImportDeadline;
import duke.command.taskCommand.taskImport.CommandImportEvent;
import duke.command.taskCommand.taskImport.CommandImportToDo;
import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.Task;
import duke.task.model.ToDo;

public class ImportCommandFactory extends CommandFactory {
    private Command executeImportJsonTaskParseStage(JsonObject jsonObj, TaskManager taskManager) {
        Task task;
        try {
            task = taskManager.objectify(jsonObj);
        } catch (Exception e) {
            return new CommandExecutionError(e, "Error transforming json to java object " + jsonObj.toString());
        }
        return this.executeImportTaskImportStage(task, taskManager);
    }
    private Command executeImportTaskImportStage(Task task, TaskManager taskManager) {
        if (task instanceof Event) {
            return new CommandImportEvent((Event) task, taskManager);
        } else if (task instanceof Deadline) {
            return new CommandImportDeadline((Deadline) task, taskManager);
        } else if (task instanceof ToDo) {
            return new CommandImportToDo((ToDo) task, taskManager);
        }
        return new CommandUnknownRequest("Unrecognised Task type.");
    }
    public Command executeImportJsonTask(JsonObject jsonObj, TaskManager taskManager) {
        return this.executeImportJsonTaskParseStage(jsonObj, taskManager);
    }
}
