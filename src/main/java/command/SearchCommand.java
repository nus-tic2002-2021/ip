package command;

import duke.Storage;
import duke.UI;
import task.List;

public class SearchCommand extends Command {
    protected String searchValue;
    public SearchCommand(String searchValue) {
        setSearchValue(searchValue);
    }
    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
    public void execute(List tasks, Storage storage, UI ui){
        tasks.printSearchList(searchValue);
    }
}