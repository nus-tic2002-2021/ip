package Duke.parser;

import Duke.exception.EmptyDescriptionException;
import Duke.exception.EmptyTaskListException;
import Duke.exception.TaskNotFoundException;
import Duke.exception.UnknownSyntaxException;
import Duke.storage.Storage;
import Duke.task.*;
import Duke.ui.ReturnMessages;
import Duke.ui.UI;

public class CMD {

    protected String fullCommand;

    protected String keyword;

    protected String detail;

    private static ReturnMessages returnMessage = new ReturnMessages();

    /**
     * Constructor command
     *
     * @param command   the command input
     *
     */
    public CMD(String command) {
        this.fullCommand = command;
        if(command.contains(CMD_Enum.TODO.getName()) || command.contains(CMD_Enum.DEADLINE.getName()) || command.contains(CMD_Enum.EVENT.getName())){
            try {
                String[] toWords = command.split(" ", 2);
                this.keyword = toWords[0];
                this.detail = toWords[1];
            }
            catch(ArrayIndexOutOfBoundsException aio) {
                if(command.contains(CMD_Enum.TODO.getName()) || command.contains(CMD_Enum.DEADLINE.getName()) || command.contains(CMD_Enum.EVENT.getName())){
                    throw new EmptyDescriptionException(keyword);
                }
                else {
                    throw new UnknownSyntaxException(command);
                }
            }
        }
    }

    public CMD() {

    }

    /**
     * Base class for different execution
     *
     * @param taskList the full task list for saving purpose
     * @param ui       UI object
     * @param storage  storage object
     *
     */
    public boolean execute(TaskList taskList, UI ui, Storage storage){
        return false;
    }

    /**
     * Replies a done/add command
     *
     * @param taskList gets the full task list
     *
     */
    public  void reply(TaskList taskList) throws EmptyTaskListException, UnknownSyntaxException, TaskNotFoundException {
        if(fullCommand.contains("done")){
            try {
                String[] doneCmd = fullCommand.split(" ");
                taskList.getTask(Integer.parseInt(doneCmd[1]) - 1).setDone();
                //taskList[Integer.parseInt(doneCmd[1]) - 1].setDone();
                returnMessage.taskDoneFeedback();
                System.out.println("     " + taskList.getTask(Integer.parseInt(doneCmd[1]) - 1).toString());
                returnMessage.separator();
                return;
            }
            catch(NumberFormatException nf){
                throw new TaskNotFoundException("task not found");
            }
        }
        returnMessage.taskAddFeedback();
        try {
            System.out.println("     " + taskList.getLastTask().toString());
            System.out.println("     Now you have " + taskList.getListSize() + " tasks in the list.");
            returnMessage.separator();
        }
        catch(ArrayIndexOutOfBoundsException a){
            throw new EmptyTaskListException("empty task");
        }
    }
}
