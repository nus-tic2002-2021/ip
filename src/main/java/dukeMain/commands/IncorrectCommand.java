package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.tasks.TaskList;

public class IncorrectCommand extends Command {
    private String errormessage;

    public IncorrectCommand(String errormessage){
        this.errormessage = errormessage;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // this is implemented by child class.
        ui.showError(errormessage);
//        ui.printList(tasks.getTaskList());
    }
}
