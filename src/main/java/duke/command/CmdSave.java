package duke.command;

import duke.storage.FileAccess;
import duke.task.TaskList;
import duke.task.TaskType;

public class CmdSave {
    public static void run(TaskList myList, FileAccess fileAccess){
        String listOfTaskString = readTask_ConvertToString(myList);
        fileAccess.saveProgressIntoFile(myList, listOfTaskString);
    }

    /**
     * Read a list of task and convert it into string
     *
     * @param myList TaskList that contains the list of task
     */
    private static String readTask_ConvertToString(TaskList myList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myList.getNumOfItem(); i++) {
            String taskDetail = myList.getTaskDetail(i);
            boolean isDone = myList.getTaskDoneStatus(i);
            String isDoneString = (isDone ? "1" : "0");
            String taskType = TaskType.taskTypeToString(myList.getTaskType(i));

            sb.append(taskType).append("|");
            sb.append(isDoneString).append("|");
            sb.append(taskDetail).append("|\n");
        }
        return sb.toString();
    }
}
