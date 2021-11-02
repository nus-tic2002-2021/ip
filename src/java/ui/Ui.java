package java.ui;

import java.io.IOException;
import java.task.TaskList;

/**
 * Interface class for User Interface
 *
 * @author  Kang Teng
 * @version 8.0
 * @since   2021-09-01
 */

public interface Ui {

    Message MESSAGE = new Message();

    // Message Related <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    void msgGreet();
    void msgAssignTask(TaskList myList, int taskNumber);
    void msgAssignTaskDeadlineTaskDate(TaskList myList, int taskNumber);
    void msgAssignTaskDeadlineTaskDateTaskTime(TaskList myList, int taskNumber);
    void msgAssignTaskEventTaskDate(TaskList myList, int taskNumber);
    void msgAssignTaskEventTaskDateTaskTimeStart(TaskList myList, int taskNumber);
    void msgAssignTaskEventTaskDateTaskTimeStartTaskTimeEnd(TaskList myList, int taskNumber);
    void msgSave();
    void msgSLoad();
    void msgAskUserSetTaskPriority();
    void msgMarkDone(TaskList myList, int taskNumber);
    void msgList(TaskList myList);
    void msgBlankBeforeTaskDetail();
    void msgTaskDetail(TaskList myList, int taskNumber);
    void msgError(Exception e);
    void msgInvalidInput();
    void msgInvalidInputMissingDescription();
    void msgInvalidInputMissingDay();
    void msgInvalidInputMissingTime();
    void msgInvalidInputWrongDateTimeFormat();
    void msgInvalidInputWrongDateTimeStartEndFormat();
    void msgInvalidInputTimeStartLaterThanTimeEnd();
    void msgRemoveItem(TaskList myList, int taskNumber);
    void msgWrongTaskNumber();
    void msgBye() throws IOException;
    void msgBuddhaProtectMe() throws IOException;
}
