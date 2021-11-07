package command;

import error.DukeException;
import storage.Storage;
import ui.UI;
import task.List;

public class SearchCommand extends Command {
    protected String searchValue;
    public SearchCommand(CommandType action, String searchValue) {
        setAction(action);
        setSearchValue(searchValue);
    }
    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
    public void execute(List tasks, Storage storage, UI ui){
        try {
            switch (action) {
            case FIND:
                tasks.printSearchList((searchValue));
                break;
            case VIEW:
                tasks.printSchedule(searchValue);
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}