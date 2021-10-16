package TaskList;
import Exception_Handler.*;
import UI.ReturnMessages;

import java.util.ArrayList;

public class TaskList {
    protected int listSize;
    private ArrayList<Task> taskList;
    private static ReturnMessages printer = new ReturnMessages();

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void deleteTask(int taskID) throws TaskNotFoundException, EmptyTaskListException{
        int taskPosition = taskID -1;
        if(taskPosition > this.listSize || taskPosition < 0){
            throw new TaskNotFoundException();
        }
        System.out.println("     Noted. I've removed this task: " );
        System.out.println("     " + taskList.get(taskPosition).toString());
        this.taskList.remove(taskPosition);
        this.listSize--;
        if(this.listSize == 0){
            throw new EmptyTaskListException();
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
            throw new EmptyTaskListException();
        }
        int numbering = 1;
        System.out.println("     Here are the tasks in your list:");
        for(Task t : taskList){
            System.out.println("    "+numbering+"."+t.toString());
            numbering++;
        }
        printer.separator();
    }
}
