package src.java.ui;

import src.java.task.TaskList;

import java.io.IOException;

public interface Ui {

    Message message = new Message();

    // Message Related <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void msgGreet();
    public void msgAssignTask(TaskList myList, int taskNumber);
    public void msgAssignTaskDeadline(TaskList myList, int taskNumber);
    public void msgAssignTaskEvent(TaskList myList, int taskNumber);
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
    public void msgRemoveItem(TaskList myList, int taskNumber);
    public void msgWrongTaskNumber();
    public void msgBye() throws IOException;
    public void msgBuddahProtectMe() throws IOException;
}
