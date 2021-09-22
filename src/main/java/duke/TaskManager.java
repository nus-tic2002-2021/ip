package duke;

import duke.task.aggregator.TaskList;
import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.Task;
import duke.task.model.ToDo;

import java.time.LocalDateTime;

public class TaskManager {
    private TaskList _activeTasks = new TaskList();
    private int _serialNo = 0;



    private int rollSerialNo() {
        int no = this._serialNo;
        this._serialNo++;
        return no;
    }
    public ToDo addNewToDo(String taskDescription) {
        ToDo task = new ToDo(taskDescription,this.rollSerialNo(),false);
        this._activeTasks.addTask(task);
        return task;
    }

    public Event addNewEvent(String taskDescription, String from, String to) {
        Event event = new Event(taskDescription, from, to, this.rollSerialNo(), false);
        this._activeTasks.addTask(event);
        return event;
    }
    public Deadline addNewDeadline(String taskDescription, String deadlineString) {
        Deadline deadLine = new Deadline(taskDescription, deadlineString, this.rollSerialNo(), false);
        this._activeTasks.addTask(deadLine);
        return deadLine;
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
