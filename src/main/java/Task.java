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

    public String getCompletedSymbol() {
        if (Completed) {
            return "[X]";
        } else {
            return "[]";
        }
    }
}
