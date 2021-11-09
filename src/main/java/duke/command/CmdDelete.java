package duke.command;

import duke.task.TaskList;
import duke.ui.Message;

public class CmdDelete {

    public static void run(TaskList myList, String userInput) {
        try {
            int taskNumber = Integer.parseInt(userInput.substring(7));
            Message.msgRemoveItem(myList, taskNumber - 1);
            myList.removeItem(taskNumber - 1);
        } catch (Exception e) {
            Message.msgInvalidTaskNumber();
        }
    }
}
