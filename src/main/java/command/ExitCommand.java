package command;

import storage.Storage;
import ui.UI;
import task.List;

public class ExitCommand extends Command{
    public ExitCommand(){

    }
    public void execute(List tasks, Storage storage, UI ui){
        tasks.saveList();
        ui.printExit();
        storage.saveFile(tasks.getSave());
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
