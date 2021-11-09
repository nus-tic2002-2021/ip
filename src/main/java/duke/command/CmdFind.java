package duke.command;

import duke.task.TaskList;
import duke.ui.Message;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CmdFind {

    public static void run(TaskList myList, String userInput){
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

        Message.msgTaskFound(myList, taskNumberContainingSearchTerm);
    }

    private static boolean hasStringContain(String sourceString, String searchString) {
        String pattern = "\\b" + searchString + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(sourceString);
        return m.find();
    }
}
