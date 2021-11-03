package command;

import exception.DukeException;
import filewriter.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import java.util.ArrayList;

/**
 *  View tasks in the form of a schedule e.g., view the schedule for a specific date
 */
public class ViewScheduleCommand extends Command {
    String schedule;

    /**
     * Constructor for ViewScheduleCommand.
     * @param schedule Searches for all tasks with dateTime schedule.
     */
    public ViewScheduleCommand(String schedule) {
        super.type = FullCommand.VIEW;
        this.schedule = schedule;
    }


    /**
     * Checks if command is an ExitCommand.
     * @return false.
     */
    public boolean isExit() {
        assert (!super.type.getActivityName().equals("bye"));
        return false;
    }

    /**
     * Generates a TaskList shortlist containing only tasks with the dateTime schedule.
     * Passes TaskList as an argument for ui.showSchedule to display to user.
     * @param tasks TaskList containing all Tasks.
     * @param ui Instance of user interface to print feedback to user.
     * @param storage Updates data record of TaskList in storage.filepath if needed.
     * @throws DukeException when command cannot be found.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> shortlist = new ArrayList<>();
        try {
            for (Task task : tasks.getList()) {
                if (schedule.equals("all")) {
                    if (task.toString().contains("/")) {
                        shortlist.add(task);
                    }
                } else if (task.toString().contains(schedule)) {
                    shortlist.add(task);
                }
            }
            if (schedule.equals("all")) {
                ui.showAllSchedule(new TaskList(shortlist));
            } else {
                ui.showSchedule(new TaskList(shortlist));
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("You do not have any tasks in the mentioned schedule.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter the schedule "
                    + "(i.e. specific date) that you wish to search in the list.");
        }
    }
}