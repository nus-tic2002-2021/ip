package duke.command;

import duke.storage.FileAccess;
import duke.task.TaskList;
import duke.task.TaskPriority;
import duke.task.TaskType;

public class CmdSave {

    private static FileAccess fileAccess;
    private static TaskList myList;
    private static StringBuilder sb;

    private static String taskTypeInString;
    private static String isDoneInString;
    private static String taskDetail;
    private static String taskPriorityInString;
    private static String taskDate;
    private static String timeStart;
    private static String timeEnd;

    public CmdSave(TaskList myList, FileAccess fileAccess) {
        this.myList = myList;
        this.fileAccess = fileAccess;
        sb = new StringBuilder();
    }

    public static void run() {
        String listOfTaskString = generateListOfTaskInString();
        fileAccess.saveProgressIntoFile(listOfTaskString);
    }

    /**
     * Read a list of task and convert it into string
     */
    private static String generateListOfTaskInString() {

        for (int taskIndex = 0; taskIndex < myList.getNumOfItem(); taskIndex++) {
            clearString();
            appendBasic(taskIndex);
            appendAdvance(taskIndex);
            appendEnd();
        }
        return sb.toString();
    }

    private static void clearString() {
        taskTypeInString = "null";
        isDoneInString = "null";
        taskDetail = "null";
        taskPriorityInString = "null";
        taskDate = "null";
        timeStart = "null";
        timeEnd = "null";
    }

    private static StringBuilder appendBasic(int taskIndex) {

        taskTypeInString = TaskType.taskTypeToString(myList.getTaskType(taskIndex));

        boolean isDone = myList.getTaskDoneStatus(taskIndex);
        isDoneInString = (isDone ? "1" : "0");

        taskDetail = myList.getTaskDetail(taskIndex);

        TaskPriority taskPriority = myList.getTaskPriority(taskIndex);
        taskPriorityInString = taskPriority.toStringInNumber();

        sb.append(taskIndex).append("|");
        sb.append(taskTypeInString).append("|");
        sb.append(isDoneInString).append("|");
        sb.append(taskDetail).append("|");
        sb.append(taskPriorityInString).append("|");

        return sb;
    }

    private static StringBuilder appendAdvance(int taskIndex) {

        switch (taskTypeInString) {
        case "D":
            appendDeadline(taskIndex);
            break;
        case "E":
            appendEvent(taskIndex);
            break;
        default:
            break;
        }

        return sb;
    }

    private static StringBuilder appendDeadline(int taskIndex) {

        taskDate = myList.getTaskDeadLineTaskDateInString(taskIndex);

        timeStart = myList.getTaskDeadLineTaskTimeInString(taskIndex);

        sb.append(taskDate).append("|");
        sb.append(timeStart).append("|");

        return sb;
    }

    private static StringBuilder appendEvent(int taskIndex) {

        taskDate = myList.getTaskEventTaskDateInString(taskIndex);

        timeStart = myList.getTaskEventTaskTimeStartInString(taskIndex);
        timeEnd = myList.getTaskEventTaskTimeEndInString(taskIndex);

        sb.append(taskDate).append("|");
        sb.append(timeStart).append("|");
        sb.append(timeEnd).append("|");

        return sb;
    }

    private static StringBuilder appendEnd() {
        sb.append(";\n");
        return sb;
    }
}
