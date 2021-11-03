package command;

import exception.DukeException;
import filewriter.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Abstract class for all other command classes to extend to (e.g. DeleteCommand, etc.), except FulLCommand enum.
 */
public abstract class Command {
    FullCommand type;

    /**
     * Checks to see if type equals FullCommand.BYE.
     * @return boolean value to check if this is an Exit command.
     */
    public boolean isExit() {
        return type.getActivityName().equals("bye");
    }

    /**
     * Carries out the command.
     * @param tasks Current TaskList object used in this instance of Duke.
     * @param ui Instance of user interface to print feedback to user.
     * @param storage Updates data record of TaskList in storage.filepath if needed.
     * @throws DukeException Various subclass of Command may throw DukeException when
     *                       executed with invalid specification.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}