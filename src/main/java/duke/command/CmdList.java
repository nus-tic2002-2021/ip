package duke.command;

import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.MsgTaskDetail;

public class CmdList {

    /**
     * Show full list of tasks
     *
     * @param myList TaskList that contains the list of task
     */
    public static void run(TaskList myList) {
        for (int i = 0; i < myList.getNumOfItem(); i++) {
            Message.msgShowBracketWithIndex(i + 1);
            new MsgTaskDetail(myList, i).showTaskDetail();
        }
        Message.msgDashLines();
    }
}
