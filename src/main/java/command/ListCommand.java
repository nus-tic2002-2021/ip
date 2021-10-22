package command;

import duke.Storage;
import duke.UI;
import task.List;

public class ListCommand extends Command {
    public ListCommand() {

    }
    public void execute(List tasks, Storage storage, UI ui){
        tasks.printList();
    }
}