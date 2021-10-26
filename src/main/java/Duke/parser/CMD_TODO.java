package Duke.parser;

import Duke.exception.UnknownSyntaxException;
import Duke.storage.Storage;
import Duke.storage.Storage_Unschedule;
import Duke.task.*;
import Duke.ui.UI;

public class CMD_TODO extends CMD {
    private static Storage_Unschedule unscheduledRecordLoad;
    private static UnscheduledTaskList unscheduledRecord;
    /**
     * TO DO Command Constructor
     *
     */
    public CMD_TODO(String command) {
        super(command);
    }

    /**
     * Function Overloading for execution of TO DO command
     *
     * @param taskList the full task list for saving purpose
     * @param ui       UI object
     * @param storage  storage object
     *
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, Storage storage) {
        boolean success;
        try {
            if (!CMD_Enum.TODO.getName().equals(super.keyword)) throw new UnknownSyntaxException(super.keyword);
            unscheduledRecordLoad = new Storage_Unschedule();
            unscheduledRecord = new UnscheduledTaskList(unscheduledRecordLoad.load());
            System.out.println("detail: "+super.detail);
            int duration = unscheduledRecord.findRecord(super.detail);
            Task task;
            System.out.println("Duration: "+duration);
            if (duration > 0) {
                task = new ToDos(super.detail, duration);
            }
            else{
                task = new ToDos(super.detail);
            }
            taskList.addTask(task);
            success = true;
        } catch (IndexOutOfBoundsException e) {
            success = false;
            throw new UnknownSyntaxException(super.keyword);
        }

        if (success) {
            reply(taskList);
        }

        return false;
    }
}
