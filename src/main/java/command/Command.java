package command;

import exception.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.*;

public abstract class Command {

    protected boolean isExit = false;

    public Command(){}

    public Command(String str){ System.out.println(str);}

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;

    public void setExit(boolean exit){
        isExit = exit;
    }

    public boolean isExit(){
        return isExit;
    }

}
