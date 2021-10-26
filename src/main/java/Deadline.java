public class Deadline extends Task{
    protected String by;

    public Deadline(String newTask, String datetime) {
        super(newTask);
        by = datetime;
    }

    public String getCompletedSymbol() {
        if (Completed) {
            return "[D][X]";
        } else {
            return "[D][ ]";
        }
    }

    public String getTaskInfo() {
        return super.getTaskInfo() + " (by: " + by + ")";
    }

    @Override
    public String getTaskType() {
        return "D";
    }
}
