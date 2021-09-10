package classes.tasks;

import classes.commands.AddCommand;
import classes.enums.CommandType;
import classes.enums.TaskType;
import classes.commands.Command;
import exceptions.InvalidCommandFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskFactoryTest {

    @Test
    void getInstanceTodoSuccess() throws InvalidCommandFormatException {
        Command addCommand = new AddCommand(CommandType.ADD,
                TaskType.TODO.toString(), "read book");

        Task task = TaskFactory.getInstance(addCommand);
        Assertions.assertEquals(TaskType.TODO, task.getType(), "TaskFactory assertEquals Todo failed");
    }

    @Test
    void getInstanceEventSuccess() throws InvalidCommandFormatException {
        Command addCommand = new AddCommand(CommandType.ADD,
                TaskType.EVENT.toString(),
                "meet friend /at 2020-09-15");

        Task task = TaskFactory.getInstance(addCommand);
        Assertions.assertEquals(TaskType.EVENT, task.getType(), "TaskFactory assertEquals Event failed");
    }

    @Test
    void getInstanceDeadlineSuccess() throws InvalidCommandFormatException {
        Command addCommand = new AddCommand(CommandType.ADD,
                TaskType.DEADLINE.toString(),
                "return book /by 2021-09-20");

        Task task = TaskFactory.getInstance(addCommand);
        Assertions.assertEquals(TaskType.DEADLINE, task.getType(), "TaskFactory assertEquals Deadline failed");
    }

    @Test
    void getInstanceTodoFail() {
        Command addCommand = new AddCommand(CommandType.ADD,
                TaskType.TODO.toString(), "");

        InvalidCommandFormatException icfe = Assertions.assertThrows(InvalidCommandFormatException.class, () -> {
            TaskFactory.getInstance(addCommand);
        }, "TaskFactory assertThrows Todo failed.");

        Assertions.assertEquals(InvalidCommandFormatException.ERROR_TODO,
                icfe.toString(),
                "TaskFactory assertEquals ERROR_TODO failed.");
    }

    @Test
    void getInstanceEventFail() {
        Command addCommand = new AddCommand(CommandType.ADD,
                TaskType.EVENT.toString(),
                "meet friend");

        InvalidCommandFormatException icfe = Assertions.assertThrows(InvalidCommandFormatException.class, () -> {
            TaskFactory.getInstance(addCommand);
        }, "TaskFactory assertThrows Event failed.");

        Assertions.assertEquals(InvalidCommandFormatException.ERROR_EVENT,
                icfe.toString(),
                "TaskFactory assertEquals ERROR_EVENT failed.");
    }

    @Test
    void getInstanceDeadlineFail() {
        Command addCommand = new AddCommand(CommandType.ADD,
                TaskType.DEADLINE.toString(),
                "return book");

        InvalidCommandFormatException icfe = Assertions.assertThrows(InvalidCommandFormatException.class, () -> {
            TaskFactory.getInstance(addCommand);
        }, "TaskFactory assertThrows Deadline failed.");

        Assertions.assertEquals(InvalidCommandFormatException.ERROR_DEADLINE,
                icfe.toString(),
                "TaskFactory assertEquals ERROR_EVENT failed.");
    }
}