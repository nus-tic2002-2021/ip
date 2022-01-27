package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.tasks.Task;
import dukeMain.tasks.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command{
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all Task that matches the description.\n"
            + "Example: " + COMMAND_WORD + " read";

    private static String description;

    public FindCommand (String description){
        this.description = description;
    }

    /**
     * Overriding the Parent class's execute.
     * Calls printList from the ui class
     * and find the tasks that matches the description.
     *
     * @param tasks TaskList class.
     * @param ui Ui class.
     * @param storage Storage class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // this is implemented by child class.

        ArrayList<Task> tkl = tasks.findList(description);
        if(tkl.size() != 0){
            String isOrAre = (tkl.size() > 1) ? "are" : "is";
            ui.print("We have found " + tkl.size() + " number of Task matching your description\n Here "+isOrAre+" the list");
            ui.printList(tkl);
        }else{
            ui.print("There is not Task matching your description. Please try again with the full command.");
        }

    }
}
