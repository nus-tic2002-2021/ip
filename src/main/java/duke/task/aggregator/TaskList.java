package duke.task.aggregator;

import duke.task.model.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;


public class TaskList {
    private final ConcurrentHashMap<Integer, Task> _map = new ConcurrentHashMap<>();

    // helper class
    private Integer getTaskKey(Task task) {
        return task.getTaskId();
    }

    public TaskList() {
    }

    public void addTask(Task t) {
        this._map.put(this.getTaskKey(t), t);
    }

    private ConcurrentHashMap<Integer, Task> getContainer(){
        return this._map;
    }


    public ArrayList<Task> getAllAsArray() {
        return new ArrayList<>(this.getMap().values());
    }


    public Integer getSize() {
        return this.getMap().size();
    }


    public Boolean containsKey(Integer key) {
        return this.getMap().containsKey(key);
    }

    public Task getTaskById(Integer taskId) {
        return this.getMap().get(taskId);
    }
    public Task removeTaskById(Integer taskId) {
        Task task = this.getTaskById(taskId);
        this.getMap().remove(taskId);
        return task;
    }



    private ConcurrentHashMap<Integer, Task> getMap() {
        return this._map;
    }



}