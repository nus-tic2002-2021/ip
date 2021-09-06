package duke.coronet.task.aggregator;

import duke.coronet.task.model.Task;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class TaskList {
    private final ConcurrentHashMap<Integer, Task> _map = new ConcurrentHashMap<>();

    private ConcurrentHashMap<Integer, Task> getMap() {
        return this._map;
    }

    // helper class
    private Integer getTaskKey(Task task) {
        return task.getTaskId();
    }

    public TaskList() {
    }

    public void addTask(Task t) {
        this._map.put(this.getTaskKey(t), t);
    }

    public void removeTask(Task t) {
        this.getMap().remove(this.getTaskKey(t));
    }


    public ArrayList<Task> getAllAsArray() {
        return new ArrayList<>(this.getMap().values());
    }

    public ConcurrentHashMap<Integer, Task> getAllAsMap() {
        return this.getMap();
    }

    public Task getTaskById(Integer taskId) {
        return this.getMap().get(taskId);
    }

    public Integer getSize() {
        return this.getMap().size();
    }

    public Boolean containsKey(Integer key) {
        return this.getMap().containsKey(key);
    }


}
