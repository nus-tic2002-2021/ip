package duke.command;

import duke.task.TaskList;
import duke.ui.Message;

public class CmdDelete {

    public static void run(TaskList myList, String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
            int numOfTaskAfterDelete = myList.getNumOfItem() - 1;
            Message.msgRemoveItem(myList, taskIndex, numOfTaskAfterDelete);
            myList.removeItem(taskIndex);
        } catch (Exception e) {
            Message.msgInvalidTaskNumber();
        }
    }
}
