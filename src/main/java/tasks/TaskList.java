package tasks;

import exceptions.*;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public int getListSize() { return taskList.size(); }

    public void printTasks() {
        if(taskList.size() == 0){
            System.out.println("There's no task now :D");
        }
        else {
            System.out.println("Here are the task(s) in your list:");
            for (Task task : taskList) {
                System.out.println(task.getTaskId() + ". " + task);
            }
        }
    }

    public void setDone(int taskId) throws DukeArgumentException {
        if (taskId > taskList.size()){
            throw new DukeArgumentException();
        }
        else {
            Task doneTask = taskList.get(taskId - 1);
            doneTask.markAsDone();

            System.out.println("Amazing! The task is marked as done now:");
            System.out.println(doneTask);
        }
    }

    public void addTask(Task task){
        taskList.add(task);

        System.out.println("Got it! I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + getListSize() + " task(s) in the list");
    }
}
