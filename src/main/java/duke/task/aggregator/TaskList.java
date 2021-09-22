package duke.task.aggregator;

import duke.task.model.Task;

import java.util.Arrays;


public class TaskList {
    private final Task[] _list = new Task[100];

    // helper class
    private Integer getTaskKey(Task task) {
        return task.getTaskId();
    }

    private Integer _pos = 0;
    public TaskList() {
    }
    public void addTask(Task t) {
        this.getContainer()[_pos] = t;
        this._pos++;
    }
    private Task[] getContainer(){
        return this._list;
    }

    public Task[] getAllAsArray() {
        return Arrays.copyOfRange(this._list, 0, this._pos);
    }

    public Integer getSize() {
        return this._pos;
    }

    public Boolean containsKey(Integer taskId) {
        return this._list[taskId] != null;
    }
    public Task getTaskById(Integer taskId) {
        return this._list[taskId];
    }


}