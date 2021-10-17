package Duke.task;

import Duke.exception.EmptyTaskListException;
import Duke.exception.TaskNotFoundException;
import Duke.ui.ReturnMessages;

import java.util.ArrayList;

public class TaskList {
    protected int listSize;
    private ArrayList<Task> taskList;
    private static ReturnMessages printer = new ReturnMessages();

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(TaskList load) {
        this.listSize = load.listSize;
        this.taskList = load.taskList;
    }

    public void deleteTask(int taskID) throws TaskNotFoundException, EmptyTaskListException {
        int taskPosition = taskID -1;
        if(taskPosition > this.listSize || taskPosition < 0){
            throw new TaskNotFoundException("task not found");
        }
        System.out.println("     Noted. I've removed this task: " );
        System.out.println("     " + taskList.get(taskPosition).toString());
        this.taskList.remove(taskPosition);
        this.listSize--;
        if(this.listSize == 0){
            throw new EmptyTaskListException("empty task");
        }
        System.out.println("     Now you have " + this.listSize + " tasks in the list.");
        printer.separator();
    }

    public void addTask(Task task){
        this.taskList.add(task);
        this.listSize++;
    }

    public Task getTask(int ID){
        return taskList.get(ID);
    }

    public Task getLastTask(){
        return taskList.get(listSize-1);
    }

    public int getListSize(){
        return this.listSize;
    }

    public void save(){}
    public void printTaskList() throws EmptyTaskListException {
        printer.separator();
        if(this.listSize == 0){
            throw new EmptyTaskListException("empty task");
        }
        int numbering = 1;
        System.out.println("     Here are the tasks in your list:");
        for(Task t : taskList){
            System.out.println("    "+numbering+"."+t.toString());
            numbering++;
        }
        printer.separator();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
