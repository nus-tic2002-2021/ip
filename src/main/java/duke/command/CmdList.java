package duke.command;

import duke.task.TaskList;
import duke.ui.Message;

public class CmdList {

    /**
     * Show full list of tasks
     *
     * @param myList TaskList that contains the list of task
     */
    public static void run(TaskList myList) {
        Message.msgList(myList);

        // todo split Message stuffs put here
    }
}
