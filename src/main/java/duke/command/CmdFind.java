package duke.command;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.MsgTaskDetail;

/**
 * Command class that find a task from a tasklist
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class CmdFind {

    /**
     * Execute the find task command
     * Find a task based on the keyword input by the user
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

        if (userInput.length() <= 5) {
            Message.msgInvalidFindTerm();
            return;
        }

        String searchTerm = userInput.substring(5);
        ArrayList<Integer> taskNumberContainingSearchTerm = new ArrayList<>();
        for (int i = 0; i < myList.getNumOfItem(); i++) {
            String taskDetail = myList.getTaskDetail(i);
            if (hasStringContain(taskDetail, searchTerm)) {
                taskNumberContainingSearchTerm.add(i);
            }
        }

        if (taskNumberContainingSearchTerm.isEmpty()) {
            Message.msgInvalidFindTerm();
            return;
        }

        Message.msgTaskFoundOpeningMsg();
        showTaskFound(myList, taskNumberContainingSearchTerm);
    }

    /**
     * Check if the searchString can be found inside the sourceString
     *
     * @param sourceString String that represents the source
     * @param searchString String that represents the term used to search in the sourceString
     * @return boolean True if the term is found; false otherwise
     */
    private static boolean hasStringContain(String sourceString, String searchString) {
        String pattern = "\\b" + searchString + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(sourceString);
        return m.find();
    }

    /**
     * Display message to show a list of task found based on the term
     */
    private static void showTaskFound(TaskList myList, ArrayList<Integer> taskNumberContainingSearchTerm) {

        assert myList != null : "mylist should not be empty";
        assert taskNumberContainingSearchTerm != null : "taskNumberContainingSearchTerm should not be empty";

        int index = 1;
        for (int i = 0; i < taskNumberContainingSearchTerm.size(); i++) {
            int taskNumber = taskNumberContainingSearchTerm.get(i);
            Message.msgBlankBeforeTaskDetail();
            Message.msgShowBracketWithIndex(index);
            new MsgTaskDetail(myList, taskNumber).showTaskDetail();
            index++;
        }
        Message.msgDashLines();
    }
}
