package duke.ui;

import duke.task.TaskList;
import duke.task.TaskPriority;
import duke.task.TaskType;

/**
 * Ui class that display message about a task
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class MsgTaskDetail {

    private static TaskList myList;
    private static int taskIndex;
    private static String taskTypeInString;
    private static String isDoneInString;
    private static String taskDetail;
    private static String taskPriorityInString;
    private static String year;
    private static String month;
    private static String day;
    private static String timeStart;
    private static String timeEnd;

    /**
     * Constructor
     */
    public MsgTaskDetail(TaskList myList, int taskIndex) {
        this.myList = myList;
        this.taskIndex = taskIndex;
        taskTypeInString = getTaskTypeInString(myList, taskIndex);
        isDoneInString = getIsDoneInString(myList, taskIndex);
        taskDetail = getTaskDetail(myList, taskIndex);
        taskPriorityInString = getTaskPriority(myList, taskIndex);
    }

    /**
     * Show task detail based on the task type
     */
    public static void showTaskDetail() {
        TaskType taskType = myList.getTaskType(taskIndex);

        switch (taskType) {
        case TODOS:
            showTaskTodo();
            break;
        case EVENT:
            showTaskEvent(myList, taskIndex);
            break;
        case DEADLINE:
            showTaskDeadline(myList, taskIndex);
            break;
        default:
            break;
        }
    }

    /**
     * Show task detail of TODO type
     */
    private static void showTaskTodo() {
        Message.msgTaskTodo(taskTypeInString, isDoneInString, taskDetail, taskPriorityInString);
    }

    /**
     * Show task detail of EVENT type
     */
    private static void showTaskEvent(TaskList myList, int taskIndex) {
        year = myList.getTaskEventTaskDateYearInString(taskIndex);
        month = myList.getTaskEventTaskDateMonthInString(taskIndex);
        day = myList.getTaskEventTaskDateDayInString(taskIndex);
        timeStart = myList.getTaskEventTaskTimeStartInString(taskIndex);
        timeEnd = myList.getTaskEventTaskTimeEndInString(taskIndex);

        if (timeStart.equals("null") && timeEnd.equals("null")) {
            Message.msgTaskEvent(taskTypeInString, isDoneInString, taskDetail,
                    year, month, day, taskPriorityInString);
        } else if (timeEnd.equals("null")) {
            Message.msgTaskEvent(taskTypeInString, isDoneInString, taskDetail,
                    year, month, day, timeStart, taskPriorityInString);
        } else {
            Message.msgTaskEvent(taskTypeInString, isDoneInString, taskDetail,
                    year, month, day, timeStart, timeEnd, taskPriorityInString);
        }
    }

    /**
     * Show task detail of DEADLINE type
     */
    private static void showTaskDeadline(TaskList myList, int taskIndex) {
        year = myList.getTaskDeadLineTaskDateYearInString(taskIndex);
        month = myList.getTaskDeadLineTaskDateMonthInString(taskIndex);
        day = myList.getTaskDeadLineTaskDateDayInString(taskIndex);
        timeStart = myList.getTaskDeadLineTaskTimeInString(taskIndex);

        if (timeStart.equals("null")) {
            Message.msgTaskDeadline(taskTypeInString, isDoneInString, taskDetail,
                    year, month, day, taskPriorityInString);
        } else {
            Message.msgTaskDeadline(taskTypeInString, isDoneInString, taskDetail,
                    year, month, day, timeStart, taskPriorityInString);
        }
    }

    /**
     * Get task type in string
     *
     * @param myList    TaskList that contains the list of task
     * @param taskIndex Int that represents the index of the task
     * @return String that describes the task type
     */
    private static String getTaskTypeInString(TaskList myList, int taskIndex) {
        TaskType taskType = myList.getTaskType(taskIndex);
        return TaskType.taskTypeToString(taskType);
    }

    /**
     * Get isDone status in string
     *
     * @param myList    TaskList that contains the list of task
     * @param taskIndex Int that represents the index of the task
     * @return String that describes the is done status
     */
    private static String getIsDoneInString(TaskList myList, int taskIndex) {
        boolean isDone = myList.getTaskDoneStatus(taskIndex);
        return (isDone ? "X" : " ");
    }

    /**
     * Get task detail in string
     *
     * @param myList    TaskList that contains the list of task
     * @param taskIndex Int that represents the index of the task
     * @return String that describes the task detail
     */
    private static String getTaskDetail(TaskList myList, int taskIndex) {
        return myList.getTaskDetail(taskIndex);
    }

    /**
     * Get task priority in string
     *
     * @param myList    TaskList that contains the list of task
     * @param taskIndex Int that represents the index of the task
     * @return String that describes the priority of a task
     */
    private static String getTaskPriority(TaskList myList, int taskIndex) {
        TaskPriority taskPriority = myList.getTaskPriority(taskIndex);
        return TaskPriority.convertPriorityToString(taskPriority);
    }
}
