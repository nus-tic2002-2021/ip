public class TaskList extends Task{

    public Task[] taskList = new Task[100];
    static int counter;

    public TaskList() {
        counter = 0;
    }

    public void addTask(String description) {
        this.taskList[counter] = new Task(description);
        this.counter++;
        System.out.println("added: " + description);
    }

    public void setDone(int index) {
        this.taskList[index].setDone();
    }

    public void printTaskList() {
        for (int i = 0; i < this.counter; i++) {
            Task temp = this.taskList[i];
            System.out.println(i+1 + ". [" + temp.getStatusIcon() + "] " + temp.getDescription());
        }
    }
}