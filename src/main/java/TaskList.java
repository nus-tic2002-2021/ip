import Exception.DukeTaskNotFoundException;

import java.util.ArrayList;

public class TaskList extends Task{

//    public Task[] taskList = new Task[100];
    ArrayList<Task> taskList = new ArrayList<Task>();

    static int counter;

    public TaskList() {
        counter = 0;
    }

    public void addTask(String description, Type type, DateTime dateTime) {
        taskList.add(new Task(description, type, dateTime));
//        this.taskList[counter] = new Task(description, type, dateTime);
        System.out.println("Got it. I've added this task: ");
//        System.out.println(getTaskDetails(counter));
        System.out.println(getTaskDetails(taskList.size() - 1));
//        this.counter++;
//        System.out.println("Now you have " + this.counter +" tasks in the list.");
        System.out.println("Now you have " + taskList.size() +" tasks in the list.");
    }

    public void setDone(int index) throws DukeTaskNotFoundException {
        try{
            taskList.get(index-1).setDone();
//            this.taskList[index].setDone();
            System.out.println("Nice! I've marked this task as done: ");
            //System.out.println("[" + this.taskList[index].getStatusIcon() + "] " + this.taskList[index].getDescription());
            System.out.println(getTaskDetails(index - 1));
        } catch (Exception ex) {
            throw new DukeTaskNotFoundException("Invalid task number");
        }

    }

    public void printTaskList() {
        System.out.println("Here are the tasks in your list: ");
//        for (int i = 0; i < this.counter; i++) {
        for (int i = 0; i < taskList.size(); i++) {
//            Task temp = taskList.get(i);
            System.out.println(i + 1 + ". " + getTaskDetails(i));
        }
    }

    public String getTaskDetails(int index) {
//        Task temp = this.taskList[index];
        Task temp = taskList.get(index);
        return "[" + temp.getTaskType() + "][" + temp.getStatusIcon() + "] " + temp.getDescription() + " " + temp.getDateTime();
    }
}