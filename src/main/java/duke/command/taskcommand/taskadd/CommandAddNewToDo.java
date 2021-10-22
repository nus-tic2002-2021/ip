package duke.command.taskcommand.taskadd;

import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;
import duke.TaskManager;

import java.util.List;


public class CommandAddNewToDo extends Command {
    public CommandAddNewToDo(TaskManager taskManager, String taskDescription) {
        super(ResponseType.TASK_CREATE_TODO, List.of("create", taskDescription, taskManager.addNewToDo(taskDescription).getTaskDescription()));
    }
}