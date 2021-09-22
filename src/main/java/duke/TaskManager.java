package duke;

import duke.task.aggregator.TaskList;
import duke.task.model.Task;
import duke.task.model.ToDo;

public class TaskManager {
    private TaskList _activeTasks = new TaskList();
    private int _serialNo = 0;

    private int getSerialNo() {
        return this._serialNo;
    }

    private int rollSerialNo() {
            this._serialNo++;
        return this.getSerialNo();
    }
    public Task addNewToDo(String taskDescription) {
        Task task = new ToDo(taskDescription,this.rollSerialNo(),false);
        this._activeTasks.addTask(task);
        return task;
    }

    public Integer getSize(){
        return this._activeTasks.getSize();
    }

    public Task[] getAllAsArray(){
        return this._activeTasks.getAllAsArray();
    }

}
