package command;

import storage.Storage;
import ui.UI;
import task.List;

public class ListCommand extends Command {
    public ListCommand() {

    }
    public void execute(List tasks, Storage storage, UI ui){
        tasks.printList();
    }
}