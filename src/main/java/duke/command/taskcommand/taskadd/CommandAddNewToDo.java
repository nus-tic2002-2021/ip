package duke.command.taskcommand.taskadd;

import java.util.List;

import duke.TaskManager;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;
import duke.task.model.ToDo;


public class CommandAddNewToDo extends Command {

    /**
     * Task manager will add a new todo to its collection with provided parameters
     *
     * @param tm   task manager
     * @param desc of todo
     */

    public CommandAddNewToDo(TaskManager tm, String desc) {
        super(ResponseType.TASK_CREATING_IN_PROGRESS,
            null);
        ToDo todo = tm.addNewToDo(desc);
        this.setArgs(List.of("create", todo.getTaskId().toString(), todo.getTaskDescription()));
        this.setResponseType(ResponseType.TASK_CREATE_TODO);
    }

    public String getResponse() {

        String id = this.getArgs().get(1);
        String desc = this.getArgs().get(2);

        return "Added To Do [id #" + id + "]: " + desc + System.lineSeparator();
    }
}
