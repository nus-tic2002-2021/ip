package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.exceptions.DukeException;
import dukeMain.tasks.TaskList;

/**
 * Represents a Command being called by the users. A <code>Command</code> object
 * is the Parent class for all the types of Command.
 * It can be represented by a String 'Command' and boolean type 'exit' which is by default false.
 * It will be called by it child classes where command will be included.
 */
public class Command {
    private String command;
    private boolean exit;

    public Command(String command){
        this.command = command;
        exit = false;
    }
    protected Command() {
    }
    @Override
    public String toString() {
        return "dukeMain.commands.Command{" +
                "userCommand='" + command + '\'' +
                '}';
    }
    public String getCommand(){
        return command;
    }

    public void setCommand(String command){
        this.command = command;
    }

    public boolean isExit(){
        return exit;
    }
    public void setExit(boolean exit){
        this.exit = exit;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // this is implemented by child class.
    }
}
