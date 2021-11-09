package duke.command;

import duke.task.TaskList;
import duke.task.TaskPriority;
import duke.ui.Message;

import java.util.Scanner;

public class CmdPriority {

    /**
     * Ask user which task to set priority and change the priority accordingly
     * -- Ask user which task to be changed
     * -- Ask user what is the new priority
     * -- Update the task with new priority
     *
     * @param myList TaskList that contains the list of task
     */
    public static void run(TaskList myList) {
        boolean isTaskNumberValid = false;
        String userInputTaskNumber = "";

        boolean isPriorityNumberValid = false;
        String userInputNewPriorityNumber = "";

        // todo this one go back to ui

        Scanner in = new Scanner(System.in);

        while (isTaskNumberValid == false) {
            Message.msgAskUserSetTaskPriority();
            userInputTaskNumber = in.nextLine();
            isTaskNumberValid = hasValidTaskNumber(myList, userInputTaskNumber);
        }

        int taskNumber = Integer.parseInt(userInputTaskNumber);
        Message.msgDashLines();

        while (isPriorityNumberValid == false) {
            Message.msgAskUserWhatPriority();
            userInputNewPriorityNumber = in.nextLine();
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

    private static boolean hasValidPriorityNumber(String userInputPriorityS) {
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
     * @param taskNumber   int that indicates the task number
     * @param taskPriority TaskPriority(new one) that want to be set
     */
    private static void toUpdatePriority(TaskList myList, int taskNumber, TaskPriority taskPriority) {
        myList.setTaskPriority(taskNumber, taskPriority);
        Message.msgSetPriority(myList, taskNumber); //
    }
}
