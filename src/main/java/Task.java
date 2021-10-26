public class Task {

    protected String Description;
    protected boolean Completed;

    public Task(String newTask) {
        this.Description = newTask;
        this.Completed = false;
    }

    public String getDescription() {
        return Description;
    }

     public String getAdditionalInfo() {
        return "";
     }

    public String getCompletedSymbol() {
        if (Completed) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String getTaskInfo() {
        String TaskInfo = this.getCompletedSymbol() + " " + Description;
        return TaskInfo;
    }

    public void markCompleted() {
        Completed = true;
    }

    public String getTaskType() {
        return "";
    }
}
