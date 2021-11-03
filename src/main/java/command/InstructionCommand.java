package command;

import exception.DukeException;
import filewriter.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Displays the instructions to the user.
 */
public class InstructionCommand extends Command {

    /**
     * Constructor of instructions.
     */
    public InstructionCommand() {
        super.type = FullCommand.INSTRUCTION;
    }

    /**
     * Checks if command is an ExitCommand.
     * @return InstructionCommand is not ExitCommand.
     */
    public boolean isExit() {
        assert (!super.type.getActivityName().equals("bye"));
        return false;
    }

    /**
     * Passes TaskList as an argument for ui.readInstruction to display to user.
     * @param tasks Current TaskList object used in this instance of Duke.
     * @param ui Instance of user interface to print feedback to user.
     * @param storage Updates data record of TaskList in storage.filepath if needed.
     * @throws DukeException if the number is not in the list or no number was indicated.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.readInstruction();
    }
}