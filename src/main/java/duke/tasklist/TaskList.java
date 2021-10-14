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

    public String deleteTask(int taskId) throws DukeException {
        if (taskId > taskList.size() || taskId < 1) {
            throw new DukeException("Oops! Please specify the correct task id to remove it ☹");
        }
        else {
            Task deletedTask = taskList.get(taskId - 1);
            taskList.remove(taskId - 1);
            return deletedTask.toString();
        }
    }

    public int getSize() { return taskList.size(); }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public String setDone(int taskId) throws DukeException {
        if (taskId > taskList.size() || taskId < 1) {
            throw new DukeException("Oops! Please specify the correct task id to mark it as done ☹");
        }
        else {
            Task completedTask = taskList.get(taskId - 1);
            completedTask.setDone();
            return completedTask.toString();
        }
    }

}
