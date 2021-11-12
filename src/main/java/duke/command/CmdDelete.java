package duke.command;

import duke.task.TaskList;
import duke.ui.Message;

/**
 * Command class that delete a task from a tasklist
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class CmdDelete {

    /**
     * Execute the delete task command
     * Remove a task from a tasklist
     *
     * @param myList    TaskList that contains the list of task
     * @param userInput String
     */
    public static void run(TaskList myList, String userInput) {

        assert myList != null : "mylist should not be empty";
        assert userInput != null : "userInput should not be empty";

        try {
            int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
            int numOfTaskAfterDelete = myList.getNumOfItem() - 1;

            if (taskIndex >= myList.getNumOfItem()) {
                Message.msgInvalidTaskNumber();
                return;
            }

            Message.msgRemoveItem(myList, taskIndex, numOfTaskAfterDelete);
            myList.removeItem(taskIndex);
        } catch (Exception e) {
            Message.msgInvalidTaskNumber();
        }
    }
}
