package duke;

import duke.task.aggregator.TaskList;
import duke.task.model.Task;
import duke.task.model.ToDo;

public class TaskManager {
    private TaskList _activeTasks = new TaskList();
    private int _serialNo = 0;



    private int rollSerialNo() {
        int no = this._serialNo;
        this._serialNo++;
        return no;
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
    public Boolean containsTaskId(Integer taskId) {
        return this._activeTasks.containsKey(taskId);
    }
    public Task getTaskById(Integer taskId) {
        return this._activeTasks.getTaskById(taskId);
    }

    public Task getTaskByIdAndSetDoneStatus(Integer taskId, Boolean done) {
        Task target = this.getTaskById(taskId);
        target.setDoneStatus(done);
        return target;
    }

}
