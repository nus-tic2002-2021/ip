package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.exceptions.DukeException;
import dukeMain.tasks.TaskList;

public class Command {
    private String userCommand;
    private boolean exit;

    public Command(String command){
        userCommand = command;

    }
    protected Command() {
    }
    @Override
    public String toString() {
        return "dukeMain.commands.Command{" +
                "userCommand='" + userCommand + '\'' +
                '}';
    }
    public String getUserCommand(){
        return userCommand;
    }

    public void setUserCommand(String userCommand){
        this.userCommand = userCommand;
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
