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
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + this.taskList[index].getStatusIcon() + "] " + this.taskList[index].getDescription());
    }

    public void printTaskList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < this.counter; i++) {
            Task temp = this.taskList[i];
            System.out.println(i+1 + ". [" + temp.getStatusIcon() + "] " + temp.getDescription());
        }
    }
}