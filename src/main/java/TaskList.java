public class TaskList extends Task{

    public Task[] taskList = new Task[100];
    static int counter;

    public TaskList() {
        counter = 0;
    }

    public void addTask(String description, Type type, DateTime dateTime) {
        this.taskList[counter] = new Task(description, type, dateTime);
        System.out.println("Got it. I've added this task: ");
        System.out.println(getTaskDetails(counter));
        this.counter++;
        System.out.println("Now you have " + this.counter +" tasks in the list.");
    }

    public void setDone(int index) {
        this.taskList[index].setDone();
        System.out.println("Nice! I've marked this task as done: ");
//        System.out.println("[" + this.taskList[index].getStatusIcon() + "] " + this.taskList[index].getDescription());
        System.out.println(getTaskDetails(index));
    }

    public void printTaskList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < this.counter; i++) {
            Task temp = this.taskList[i];
            System.out.println(i+1 + ". " + getTaskDetails(i));
        }
    }

    public String getTaskDetails(int index) {
        Task temp = this.taskList[index];
        return "[" + temp.getTaskType() + "][" + temp.getStatusIcon() + "] " + temp.getDescription() + " " + temp.getDateTime();
    }
}