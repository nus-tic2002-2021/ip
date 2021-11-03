package command;

import exception.DukeException;
import filewriter.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

/**
 * Adds a new Task to Task list.
 */
public class AddCommand extends Command {
    String specifics;

    /**
     * Constructor for AddCommand.
     * @param taskType type of command/task (e.g. deadline, event, find etc).
     * @param specifics includes the command/task name as well as the date and time specially for DEADLINE and EVENT.
     */
    public AddCommand(FullCommand taskType, String specifics) {
        super.type = taskType;
        this.specifics = specifics;
    }

    /**
     * Checks if command is an ExitCommand.
     * @return false as command will be AddCommand.
     */
    public boolean isExit() {
        assert (!super.type.getActivityName().equals("bye"));
        return false;
    }

    /**
     * Adds task corresponding to command type and specifics to TaskList.
     * @param tasks TaskList to add the task to.
     * @param ui Instance of user interface to print feedback to user.
     * @param storage Updates data record of TaskList stored in storage.filepath.
     * @throws DukeException Creates new Deadline and Event, throws exception if not written in correct format.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask;
        switch (type) {
        case TODO:
            newTask = new Todo(specifics);
            break;
        case DEADLINE:
            newTask = new Deadline(specifics);
            break;
        default:
            newTask = new Event(specifics);
            break;
        }
        tasks.addTask(newTask);
        ui.readTask(newTask, tasks.taskNum);
    }
}