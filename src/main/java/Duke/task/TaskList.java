package Duke.task;

import Duke.exception.EmptyTaskListException;
import Duke.exception.TaskNotFoundException;
import Duke.ui.ReturnMessages;

import java.util.ArrayList;

public class TaskList {
    protected int listSize;
    private ArrayList<Task> taskList;
    private static ReturnMessages printer = new ReturnMessages();


    /**
     * Task list Constructor
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Task list constructor with tasks within
     * @param load - the task list that already contained task.
     */
    public TaskList(TaskList load) {
        this.listSize = load.listSize;
        this.taskList = load.taskList;
    }

    /**
     * Deletes a specific task uniquely identified via taskID
     * @param taskID - the unique identifier for each task
     * @throws TaskNotFoundException - when a task is not found
     * @throws EmptyTaskListException - when the task list is empty
     */
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

    /**
     * Adds a task to task list
     * @param task - the full task information
     */
    public void addTask(Task task){
        this.taskList.add(task);
        this.listSize++;
    }

    /**
     * Gets a task via its task ID
     * @param ID - the unique identifier for a task
     * @return - returns the task
     */
    public Task getTask(int ID){
        return taskList.get(ID);
    }

    /**
     * Gets the last task added
     * @return - return the task
     */
    public Task getLastTask(){
        return taskList.get(listSize-1);
    }

    /**
     * Gets the list size.
     * @return - list size in integer
     */
    public int getListSize(){
        return this.listSize;
    }


    public void save(){}

    /**
     * Prints the tasks within the task list
     * @throws EmptyTaskListException - thrown if task list is empty
     */
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

    /**
     * Getter for getting the task list
     * @return - returns entire task list
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
