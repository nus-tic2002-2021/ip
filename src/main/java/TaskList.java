import java.util.ArrayList;

public class TaskList {
    protected int listSize;
    ArrayList<Task> taskList;
    private static PrintHelper printer = new PrintHelper();

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void deleteTask(int taskID){
        this.taskList.remove(taskID-1);
        listSize--;
    }

    public void addTask(Task task){
        this.taskList.add(task);
        listSize++;
    }

    public Task getTask(int ID){
        return taskList.get(ID);
    }

    public Task getLastTask(){
        return taskList.get(listSize-1);
    }

    public int getListSize(){
        return listSize;
    }
    public void printTaskList() throws EmptyTaskListException{
        printer.separator();
        if(listSize == 0){
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
