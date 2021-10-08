public class Deadlines extends Task{
    protected String deadline;

    public Deadlines(String newTask, String datetime) {
        super(newTask);
        deadline = datetime;
    }
}
