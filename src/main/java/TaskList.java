public class TaskList {

    public String[] taskList = new String[100];
    static int counter;

    public TaskList() {
        counter = 0;
    }

    public void addTask(String task) {
        this.taskList[counter] = task;
        this.counter++;
        System.out.println("added: " + task);
    }

    public void printTaskList() {
        for (int i = 0; i < this.counter; i++) {
            System.out.println(i+1 + ". " + this.taskList[i]);
        }
    }
}