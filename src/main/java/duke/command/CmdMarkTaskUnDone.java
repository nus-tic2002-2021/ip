package duke.command;

import duke.task.TaskList;
import duke.ui.Message;

/**
 * Command class that mark a task as undone
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class CmdMarkTaskUnDone {
    /**
     * Execute the marking command to mark a task as done
     *
     * @param myList    TaskList that contains the list of task
     * @param userInput String
     */
    public static void run(TaskList myList, String userInput) {

        assert myList != null : "mylist should not be empty";
        assert userInput != null : "userInput should not be empty";

        if (myList.getNumOfItem() == 0) {
            Message.msgTaskListIsEmpty();
            return;
        }

        try {
            int taskNumber = Integer.parseInt(userInput.substring(7));
            myList.setTaskUnDone(taskNumber - 1);
            Message.msgMarkUnDone(myList, taskNumber - 1);
        } catch (Exception e) {
            Message.msgInvalidTaskNumber();
        }

    }
}
