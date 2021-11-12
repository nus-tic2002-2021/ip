package duke.command;

import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.MsgTaskDetail;

/**
 * Command class that list all the task in the tasklist
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class CmdList {

    /**
     * Show full list of tasks
     *
     * @param myList TaskList that contains the list of task
     */
    public static void run(TaskList myList) {

        assert myList != null : "mylist should not be empty";

        for (int i = 0; i < myList.getNumOfItem(); i++) {
            Message.msgShowBracketWithIndex(i + 1);
            new MsgTaskDetail(myList, i).showTaskDetail();
        }
        Message.msgDashLines();
    }
}
