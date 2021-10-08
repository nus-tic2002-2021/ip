public class Deadlines extends Task{
    protected String deadline;

    public Deadlines(String newTask, String datetime) {
        super(newTask);
        deadline = datetime;
    }

    public String getCompletedSymbol() {
        if (Completed) {
            return "[D][X]";
        } else {
            return "[D][ ]";
        }
    }

}
