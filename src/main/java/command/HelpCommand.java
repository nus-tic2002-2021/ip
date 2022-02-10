package command;

import storage.Storage;
import task.List;
import ui.UI;

public class HelpCommand extends Command {
    public HelpCommand() {

    }

    public void execute(List tasks, Storage storage, UI ui) {
        ui.printInstruction();
    }
}