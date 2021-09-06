package duke.coronet.command.taskCommand.taskAdd;

import duke.coronet.command.Command;
import duke.coronet.dukeUtility.enums.ResponseType;
import duke.coronet.manager.TaskManager;

import java.util.List;


public class CommandAddNewToDo extends Command {
    public CommandAddNewToDo(TaskManager taskMgr, String taskDescription) {
        super(ResponseType.TASK_CREATE_TODO, List.of("create", taskDescription, taskMgr.addNewToDo(taskDescription).getTaskDescription()));
    }
}
