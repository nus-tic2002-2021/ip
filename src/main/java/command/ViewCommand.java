package command;

import duke.Duke;
import duke.Storage;
import duke.UI;
import error.DukeException;
import task.List;

public class ViewCommand extends Command {
    protected String searchValue;
    public ViewCommand(String searchValue) {
        setSearchValue(searchValue);
    }
    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
    public void execute(List tasks, Storage storage, UI ui){
        try{
            tasks.printSchedule(searchValue);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}