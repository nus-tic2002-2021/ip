package duke.ui;

import duke.task.TaskList;
import duke.task.TaskPriority;
import duke.task.TaskType;

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

    public MsgTaskDetail(TaskList myList, int taskIndex){
        this.myList = myList;
        this.taskIndex = taskIndex;
        taskTypeInString = getTaskType_InString(myList, taskIndex);
        isDoneInString = getIsDone_InString(myList, taskIndex);
        taskDetail = getTaskDetail(myList, taskIndex);
        taskPriorityInString = getTaskPriority(myList, taskIndex);
    }

    public static void showTaskDetail(){
        TaskType taskType = myList.getTaskType(taskIndex);

        switch(taskType){
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
            // todo
        }
    }

    private static void showTaskTodo(){
        Message.msgTaskTodo(taskTypeInString, isDoneInString,taskDetail, taskPriorityInString);
    }

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

    private static void showTaskDeadline(TaskList myList, int taskIndex){
        year = myList.getTaskDeadLineTaskDateYearInString(taskIndex);
        month = myList.getTaskDeadLineTaskDateMonthInString(taskIndex);
        day = myList.getTaskDeadLineTaskDateDayInString(taskIndex);
        timeStart = myList.getTaskDeadLineTaskTimeInString(taskIndex);

        if (timeStart.equals("null")){
            Message.msgTaskDeadline(taskTypeInString, isDoneInString, taskDetail,
                    year, month, day, taskPriorityInString);
        } else {
            Message.msgTaskDeadline(taskTypeInString, isDoneInString, taskDetail,
                    year, month, day, timeStart, taskPriorityInString);
        }
    }

    private static String getTaskType_InString(TaskList myList, int taskIndex) {
        TaskType taskType = myList.getTaskType(taskIndex);
        return TaskType.taskTypeToString(taskType);
    }

    private static String getIsDone_InString(TaskList myList, int taskIndex) {
        boolean isDone = myList.getTaskDoneStatus(taskIndex);
        return (isDone ? "X" : " ");
    }

    private static String getTaskDetail(TaskList myList, int taskIndex) {
        return myList.getTaskDetail(taskIndex);
    }

    private static String getTaskPriority(TaskList myList, int taskIndex) {
        TaskPriority taskPriority = myList.getTaskPriority(taskIndex);
        return TaskPriority.convertPriorityToString(taskPriority);
    }
}
