package duke.command;

import java.util.Scanner;

import duke.task.TaskList;
import duke.task.TaskPriority;
import duke.ui.Message;

/**
 * Command class that find a task from a tasklist
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class CmdPriority {

    /**
     * Ask user which task to set priority and change the priority accordingly
     * -- Ask user which task to be changed
     * -- Ask user what is the new priority
     * -- Update the task with new priority
     *
     * @param myList TaskList that contains the list of task
     */
    public static void run(TaskList myList, Scanner scanner) {

        assert myList != null : "mylist should not be empty";
        assert scanner != null : "scanner should not be empty";

        if (myList.getNumOfItem() < 1) {
            Message.msgInvalidSetTaskListIsEmpty();
            return;
        }

        boolean isTaskNumberValid = false;
        String userInputTaskNumber = "";

        boolean isPriorityNumberValid = false;
        String userInputNewPriorityNumber = "";

        while (isTaskNumberValid == false) {
            Message.msgAskUserSetTaskPriority();
            Message.msgArrowHead();
            userInputTaskNumber = scanner.nextLine();
            isTaskNumberValid = hasValidTaskNumber(myList, userInputTaskNumber);
        }

        int taskNumber = Integer.parseInt(userInputTaskNumber);
        Message.msgDashLines();

        while (isPriorityNumberValid == false) {
            Message.msgAskUserWhatPriority();
            userInputNewPriorityNumber = scanner.nextLine();
            isPriorityNumberValid = hasValidPriorityNumber(userInputNewPriorityNumber);
        }

        int newPriorityInteger = Integer.parseInt(userInputNewPriorityNumber);

        TaskPriority taskPriority = TaskPriority.convertIntToPriority(newPriorityInteger);
        toUpdatePriority(myList, taskNumber - 1, taskPriority);
    }

    /**
     * Check the validity of task number in String
     *
     * @param myList          TaskList that contains the list of task
     * @param userInputString String
     * @return boolean True if userInput is a valid task number; False otherwise
     */
    private static boolean hasValidTaskNumber(TaskList myList, String userInputString) {

        assert myList != null : "mylist should not be empty";
        assert userInputString != null : "userInputString should not be empty";

        try {
            int userInputInt = Integer.parseInt(userInputString);
            int numOfItem = myList.getNumOfItem();
            if (userInputInt <= numOfItem) {
                return true;
            }
        } catch (Exception e) {
            Message.msgInvalidTaskNumber();
            return false;
        }
        Message.msgInvalidTaskNumber();
        return false;
    }

    /**
     * Check the validity of user input for priority number
     * <p>
     * Expected input should be 1 to 3
     *
     * @param userInputPriorityS
     * @return boolean True if userInput is "1" to "3"; False otherwise
     */
    private static boolean hasValidPriorityNumber(String userInputPriorityS) {

        assert userInputPriorityS != null : "userInputPriorityS should not be empty";

        try {
            TaskPriority taskPriority = TaskPriority.convertStringToPriority(userInputPriorityS);
            if (taskPriority == TaskPriority.HIGH) {
                return true;
            } else if (taskPriority == TaskPriority.MEDIUM) {
                return true;
            } else if (taskPriority == TaskPriority.LOW) {
                return true;
            }
        } catch (Exception e) {
            Message.msgInvalidPriority();
            return false;
        }
        Message.msgInvalidPriority();
        return false;
    }

    /**
     * Ask user which task to set priority and change the priority accordingly
     * -- Ask user which task to be changed
     * -- Ask user what is the new priority
     * -- Update the task with new priority
     *
     * @param myList       TaskList that contains the list of task
     * @param taskIndex    int that indicates the task number
     * @param taskPriority TaskPriority(new one) that want to be set
     */
    private static void toUpdatePriority(TaskList myList, int taskIndex, TaskPriority taskPriority) {

        assert myList != null : "mylist should not be empty";
        assert taskIndex >= 0 : "taskIndex should be equal or more than 0";
        assert taskPriority != null : "taskPriority should not be empty";

        myList.setTaskPriority(taskIndex, taskPriority);
        String taskPriorityInString = getTaskPriorityInString(myList, taskIndex);
        Message.msgSetPriority(taskIndex + 1, taskPriorityInString);
    }

    /**
     * Get priority of the task in String
     *
     * @param myList    TaskList that contains the list of task
     * @param taskIndex Int that represents the index of the task in tasklist
     * @return String that represents the priority of the task
     */
    private static String getTaskPriorityInString(TaskList myList, int taskIndex) {

        assert myList != null : "mylist should not be empty";
        assert taskIndex >= 0 : "taskIndex should be equal or more than 0";

        TaskPriority taskPriority = myList.getTaskPriority(taskIndex);
        return TaskPriority.convertPriorityToString(taskPriority);
    }
}
