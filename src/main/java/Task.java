public class Task {

    String Description;
    boolean Completed;
    static int TaskCount = 0;
    static Task[] Checklist = new Task[100];

    public Task(String newTask) {
        this.Description = newTask;
        this.Completed = false;
    }

    public void addTask(String newTask) {
        Task newEntry = new Task(newTask);
        Checklist[TaskCount] = newEntry;
        TaskCount++;
    }

    public void printChecklist() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < TaskCount; i++) {
            if (Checklist[i].Completed) {
                System.out.println(i + ".[X] " + Checklist[i].Description);
            } else {
                System.out.println(i + ".[] " + Checklist[i].Description);
            }
        }
    }
}
