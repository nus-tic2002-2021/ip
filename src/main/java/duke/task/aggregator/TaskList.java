package duke.task.aggregator;

import duke.task.model.Task;

import java.util.Arrays;


public class TaskList {
    private final String[] _list = new String[100];

    // helper class
    private Integer getTaskKey(Task task) {
        return task.getTaskId();
    }

    private Integer _pos = 0;
    public TaskList() {
    }
    public void addTask(String t) {
        this.getContainer()[_pos] = t;
        this._pos++;
    }
    private String[] getContainer(){
        return this._list;
    }

    public String[] getAllAsArray() {
        return Arrays.copyOfRange(this._list, 0, this._pos);
    }

    public Integer getSize() {
        return this._pos;
    }


}