package duke.coronet.manager;

import duke.coronet.task.aggregator.TaskList;
import duke.coronet.task.model.Deadline;
import duke.coronet.task.model.Task;
import duke.coronet.task.model.ToDo;

import java.time.LocalDateTime;
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
    public Deadline addNewDeadline(String taskDescription, LocalDateTime deadline) {
        Deadline deadLine = new Deadline(taskDescription, deadline, this.rollSerialNo(), false);
        this._activeTasks.addTask(deadLine);
        this._serialNo++;
        return deadLine;
    }
    public ArrayList<Task> getAllAsArray() {
        return this.getActiveTasks().getAllAsArray();
    }
    public Integer getSize() {
        return this.getActiveTasks().getSize();
    }
    public Boolean containsTaskId(Integer taskId) {
        return this.getActiveTasks().containsKey(taskId);
    }
    public Task getTaskByIdAndSetDone(Integer taskId, Boolean done) {
        Task target = this.getTaskById(taskId);
        target.setDoneStatus(done);
        return target;
    }
    public Task getTaskById(Integer taskId) {
        return this.getActiveTasks().getTaskById(taskId);
    }

}
