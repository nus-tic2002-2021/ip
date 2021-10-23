package Duke.parser;

import Duke.exception.EmptyDescriptionException;
import Duke.exception.EmptyTaskListException;
import Duke.exception.TaskNotFoundException;
import Duke.exception.UnknownSyntaxException;
import Duke.storage.Storage;
import Duke.task.*;
import Duke.ui.ReturnMessages;
import Duke.ui.UI;

/**
 * base cmd class
 * CMD is the base class of all command objects. It contains some public attributes and methods.
 * Each class that inherits CMD only needs to perform its own duties.
 */
public class CMD {

    /**
     * command user input
     */
    protected String fullCommand;

    /**
     * command to be executed
     */
    protected String keyword;

    /**
     * command parameters
     */
    protected String detail;

    private static ReturnMessages returnMessage = new ReturnMessages();

    /**
     * This construction method tells the command to parse into keyword and detail(If needed)
     * @param command {@code fullCommand} user input
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
     * execute cmd
     * @param taskList user generate
     * @param ui show interactive information
     * @param storage info where to storage
     * @return if true, the program stops, otherwise it starts
     */
    public boolean execute(TaskList taskList, UI ui, Storage storage){
        return false;
    }

    /**
     * Replies a done/add command
     *
     * @param taskList gets the full task list
     * @throws EmptyTaskListException - thrown if task list is empty
     * @throws UnknownSyntaxException - thrown if syntax is unknown
     * @throws TaskNotFoundException - thrown if task is not found
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
