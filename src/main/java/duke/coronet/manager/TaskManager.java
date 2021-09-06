package duke.coronet.manager;

import duke.coronet.task.aggregator.TaskList;
import duke.coronet.task.model.Task;
import duke.coronet.task.model.ToDo;

import java.util.ArrayList;

public class TaskManager {

    private TaskList _activeTasks = new TaskList();
    private TaskList _deletedTasks = new TaskList();
    private int _serialNo = 0;

    private int rollSerialNo() {
        while (this.getActiveTasks().containsKey(this._serialNo) || this._deletedTasks.containsKey(this._serialNo)) {
            this._serialNo++;
        }
        return this.getSerialNo();
    }
    private TaskList getActiveTasks() {
        return this._activeTasks;
    }
    private int getSerialNo() {
        return this._serialNo;
    }
    public ToDo addNewToDo(String taskDescription) {
        ToDo todo = new ToDo(taskDescription, this.rollSerialNo(), false);
        this._activeTasks.addTask(todo);
        return todo;
    }
    public ArrayList<Task> getAllAsArray() {
        return this.getActiveTasks().getAllAsArray();
    }
    public Integer getSize() {
        return this.getActiveTasks().getSize();
    }
}
