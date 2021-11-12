package duke.command;

import duke.storage.FileAccess;
import duke.task.TaskList;
import duke.task.TaskPriority;
import duke.task.TaskType;

/**
 * Command class that save all the task from the current taskList to progress.txt
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

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

    /**
     * Constructor
     */
    public CmdSave(TaskList myList, FileAccess fileAccess) {
        this.myList = myList;
        this.fileAccess = fileAccess;
        sb = new StringBuilder();
    }

    /**
     * Execute the save
     */
    public static void run() {
        String listOfTaskString = generateListOfTaskInString();
        fileAccess.saveProgressIntoFile(listOfTaskString);
    }

    /**
     * Read a list of task and convert it into string
     * <p>
     * calls the following methods to generate the string
     * setStringToNull();
     * appendBasic(taskIndex);
     * appendAdvance(taskIndex);
     * appendEnd();
     *
     * @return String that represents the string to be saved in text
     */
    private static String generateListOfTaskInString() {

        for (int taskIndex = 0; taskIndex < myList.getNumOfItem(); taskIndex++) {
            setStringToNull();
            appendBasic(taskIndex);
            appendAdvance(taskIndex);
            appendEnd();
        }
        return sb.toString();
    }

    /**
     * set the private variables to "null"
     * <p>
     * taskTypeInString = "null";
     * isDoneInString = "null";
     * taskDetail = "null";
     * taskPriorityInString = "null";
     * taskDate = "null";
     * timeStart = "null";
     * timeEnd = "null";
     *
     * @return StringBuilder
     */
    private static void setStringToNull() {
        taskTypeInString = "null";
        isDoneInString = "null";
        taskDetail = "null";
        taskPriorityInString = "null";
        taskDate = "null";
        timeStart = "null";
        timeEnd = "null";
    }

    /**
     * append basic variables to the string builder
     * <p>
     * taskIndex
     * taskType
     * isDone
     * taskDetail
     * taskPriority
     *
     * @return StringBuilder
     */
    private static StringBuilder appendBasic(int taskIndex) {

        taskTypeInString = TaskType.taskTypeToString(myList.getTaskType(taskIndex));

        boolean isDone = myList.getTaskDoneStatus(taskIndex);
        isDoneInString = (isDone ? "1" : "0");

        taskDetail = myList.getTaskDetail(taskIndex);

        TaskPriority taskPriority = myList.getTaskPriority(taskIndex);
        taskPriorityInString = taskPriority.toStringInNumber();

        assert taskTypeInString != null : "taskTypeInString should not be empty";
        assert taskDetail != null : "taskDetail should not be empty";
        assert taskPriority != null : "taskPriority should not be empty";

        sb.append(taskIndex).append("|");
        sb.append(taskTypeInString).append("|");
        sb.append(isDoneInString).append("|");
        sb.append(taskDetail).append("|");
        sb.append(taskPriorityInString).append("|");

        return sb;
    }

    /**
     * append advance variables to the string builder based on the variable types
     * <p>
     * date`
     * start time
     * end time
     * <p>
     * depending on the taskType, different String is appended
     *
     * @return StringBuilder
     */
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

    /**
     * append date and start time to DEADLINE type task
     *
     * @return StringBuilder
     */
    private static StringBuilder appendDeadline(int taskIndex) {

        taskDate = myList.getTaskDeadLineTaskDateInString(taskIndex);

        timeStart = myList.getTaskDeadLineTaskTimeInString(taskIndex);

        sb.append(taskDate).append("|");
        sb.append(timeStart).append("|");

        return sb;
    }

    /**
     * append date, start time, and end time to EVENT type task
     *
     * @return StringBuilder
     */
    private static StringBuilder appendEvent(int taskIndex) {

        taskDate = myList.getTaskEventTaskDateInString(taskIndex);

        timeStart = myList.getTaskEventTaskTimeStartInString(taskIndex);
        timeEnd = myList.getTaskEventTaskTimeEndInString(taskIndex);

        sb.append(taskDate).append("|");
        sb.append(timeStart).append("|");
        sb.append(timeEnd).append("|");

        return sb;
    }

    /**
     * append ;\n to the StringBuilder
     *
     * @return StringBuilder
     */
    private static StringBuilder appendEnd() {
        sb.append(";\n");
        return sb;
    }
}
