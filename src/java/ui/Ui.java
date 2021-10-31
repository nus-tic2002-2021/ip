package src.java.ui;

import src.java.task.TaskList;

import java.io.IOException;

public interface Ui {

    Message message = new Message();

    // Message Related <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void msgGreet();
    public void msgAssignTask(TaskList myList, int taskNumber);
    public void msgAssignTaskDeadline_TaskDate(TaskList myList, int taskNumber);
    public void msgAssignTaskDeadline_TaskDate_TaskTime(TaskList myList, int taskNumber);
    public void msgAssignTaskEvent_TaskDate(TaskList myList, int taskNumber);
    public void msgAssignTaskEvent_TaskDate_TaskTimeStart(TaskList myList, int taskNumber);
    public void msgAssignTaskEvent_TaskDate_TaskTimeStart_TaskTimeEnd(TaskList myList, int taskNumber);
    public void msgEcho(String s);
    public void msgSave();
    public void msgSLoad();
    public void msgMarkDone(TaskList myList, int taskNumber);
    public void msgList(TaskList myList);
    public void msgBlankBeforeTaskDetail();
    public void msgTaskDetail(TaskList myList, int taskNumber);
    public void msgError(Exception e);
    public void msgInvalidInput();
    public void msgInvalidInputMissingDescription();
    public void msgInvalidInputMissingDay();
    public void msgInvalidInputMissingTime();
    public void msgInvalidInputWrongDateTimeFormat();
    public void msgInvalidInputWrongDateTimeStartEndFormat();
    public void msgInvalidInputTimeStartLaterThanTimeEnd();
    public void msgRemoveItem(TaskList myList, int taskNumber);
    public void msgWrongTaskNumber();
    public void msgBye() throws IOException;
    public void msgBuddahProtectMe() throws IOException;
}
