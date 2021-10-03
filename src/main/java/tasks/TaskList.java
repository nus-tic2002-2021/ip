package tasks;

import exceptions.*;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }

    public int getListSize() { return taskList.size(); }

    public String setDone(int taskId) throws DukeException {
        if (taskId > taskList.size() || taskId < 1){
            throw new DukeException("Task with id " + taskId + " is not found.");
        }
        else {
            Task doneTask = taskList.get(taskId - 1);
            doneTask.markAsDone();
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

    public void addTask(Task task){
        taskList.add(task);
    }

    public ArrayList<Task> getTasks() {
        return taskList;
    }
}
