package duke.command;

import duke.task.TaskList;
import duke.ui.Message;

public class CmdMarkTaskDone {

    public static void run(TaskList myList, String userInput) {
        int taskNumber = Integer.parseInt(userInput.substring(5));
        myList.setTaskDone(taskNumber - 1);
        Message.msgMarkDone(myList, taskNumber - 1);
    }
}
