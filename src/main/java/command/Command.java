package command;

import exception.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Parent class of all executable commands
 */
public abstract class Command {

    protected boolean isExit = false;

    public Command(){}

    public Command(String str){ System.out.println(str);}

    /**
     * abstract method to be implemented by children class
     * @param taskList is the task list
     * @param ui to print out message on screen
     * @param storage to read and write change to task.txt
     * @throws IOException when file not found
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;

    public void setExit(boolean exit){
        isExit = exit;
    }

    public boolean isExit(){
        return isExit;
    }

}
