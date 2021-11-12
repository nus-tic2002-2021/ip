package duke.command;

import duke.task.TaskList;
import duke.ui.Message;

/**
 * Command class that mark a task as done
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class CmdMarkTaskDone {

    /**
     * Execute the marking command to mark a task as done
     *
     * @param myList    TaskList that contains the list of task
     * @param userInput String
     */
    public static void run(TaskList myList, String userInput) {

        assert myList != null : "mylist should not be empty";
        assert userInput != null : "userInput should not be empty";

        int taskNumber = Integer.parseInt(userInput.substring(5));
        myList.setTaskDone(taskNumber - 1);
        Message.msgMarkDone(myList, taskNumber - 1);
    }
}
