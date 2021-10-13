package duke.tasklist;

import duke.exception.*;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public int getListSize() { return taskList.size(); }

    public String setDone(int taskId) throws DukeException {
        if (taskId > taskList.size() || taskId < 1){
            throw new DukeException("Task with id " + taskId + " is not found.");
        }
        else {
            Task doneTask = taskList.get(taskId - 1);
            doneTask.setDone();
            return doneTask.toString();
        }
    }

    public String deleteTask(int taskId) throws DukeException {
        if (taskId > taskList.size() || taskId < 1){
            throw new DukeException("Task with id " + taskId + " is not found.");
        }
        else {
            Task deletedTask = taskList.get(taskId - 1);
            taskList.remove(taskId - 1);
            return deletedTask.toString();
        }
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

}
