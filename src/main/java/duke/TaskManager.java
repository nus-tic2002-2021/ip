package duke;

import duke.task.aggregator.TaskList;

public class TaskManager {
    private TaskList _activeTasks = new TaskList();

    public String addNewToDo(String taskDescription) {
        String task = taskDescription;

        this._activeTasks.addTask(task);
        return task;
    }

    public Integer getSize(){
        return this._activeTasks.getSize();
    }

    public String[] getAllAsArray(){
        return this._activeTasks.getAllAsArray();
    }

}
