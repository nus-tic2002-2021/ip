package duke.command;

import duke.DukeException;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    String taskName;
    LocalDate on;

    EventCommand(String argumentText) throws DukeException, DateTimeParseException {
        String[] eventParts = argumentText.split(" /at ");
        if (eventParts[0].equals("") || eventParts[1].equals("")) {
            throw new DukeException("The description of an event cannot be empty");
        } else {
            this.taskName = eventParts[0];
            this.on = LocalDate.parse(eventParts[1]);
        }
    }

    @Override
    public CommandResult execute() {
        Task newEvent = new Event(taskName, false, on);
        taskList.addTasks(newEvent);
        return new CommandResult(addTaskSuccess(newEvent));
    }
}
