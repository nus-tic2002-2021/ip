package duke.task.aggregator;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import duke.task.model.Task;


public class TaskList {
    private final ConcurrentHashMap<Integer, Task> container = new ConcurrentHashMap<>();

    public TaskList() {
    }


    public ConcurrentHashMap<Integer, Task> getContainer() {
        return this.container;
    }


    public ArrayList<Task> getAllAsArray() {
        return new ArrayList<>(this.getContainer().values());
    }

    private Integer getTaskKey(Task task) {
        return task.getTaskId();
    }

    public void addTask(Task t) {
        this.container.put(this.getTaskKey(t), t);
    }

    public Integer getSize() {
        return this.getContainer().size();
    }

    public Boolean containsKey(Integer key) {
        return this.getContainer().containsKey(key);
    }

    public Task getTaskById(Integer taskId) {
        return this.getContainer().get(taskId);
    }

    /**
     * Remove a task in a container by task id
     *
     * @param taskId
     * @return
     */
    public Task removeTaskById(Integer taskId) {
        Task task = this.getTaskById(taskId);
        this.getContainer().remove(taskId);
        return task;
    }

}
