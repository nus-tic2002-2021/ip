package app;

import task.Task;
import task.TaskFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DukeController {

    private List<Task> currentTasks;
    private Map<String, String> parsedUserInputs;

    public DukeController() {
        this.currentTasks = new ArrayList<>();
    }

    public void setParsedUserInputs(Map<String, String> parsedUserInputs) {
        this.parsedUserInputs = parsedUserInputs;
    }

    public void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i< currentTasks.size(); i++){
            System.out.println(i + 1 + "." + currentTasks.get(i));
        }
    }

    public void addTask() {
        Task newTask = creatTask();
        addUniqueTask(newTask);
    }

    private Task creatTask() {
        return TaskFactory.creatTask(parsedUserInputs);
    }

    private void addUniqueTask(Task newTask) {

        if (!currentTasks.contains(newTask)){
            currentTasks.add(newTask);
            System.out.println("Got it. I've added this task: \n" +
                    " " + newTask + "\nNow you have " + currentTasks.size() + " tasks in the list.");
        } else {
            System.out.println("The same task has been added previously, please enter another task");
        }
    }

    public void changeTaskStatus() {
        /*
        The list of errors handled by this method:
        a. list of task is empty
        b. index exceeds the number of tasks
         */
        //make sure the index is not out of the list
        int idx = Integer.parseInt(parsedUserInputs.get("NameOrIndex")) - 1;
        try {
            Task taskDone = currentTasks.get(idx);
            taskDone.setDone(true);
            System.out.println("Nice! I've marked this task as done: \n" + taskDone);
        } catch (NullPointerException e) {
            System.out.println("Oops you don't have that many tasks, please enter a number <= " + currentTasks.size());
        } catch (IndexOutOfBoundsException e){
            System.out.println("Oops the index is wrong, please enter a valid index");
        }
    }

    public void deleteTask() {
        int idx = Integer.parseInt(parsedUserInputs.get("NameOrIndex")) - 1;
        try {
            Task removedTask = currentTasks.remove(idx);
            System.out.println("Noted. I've removed this task: \n" + removedTask +
                    "\nNow you have " + currentTasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops the index is wrong, please enter a valid index");
        }
    }
}
