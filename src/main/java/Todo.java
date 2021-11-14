public class Todo extends Task {
    public boolean isDone;

    public Todo(String desc, int id) {
        super(desc, id);
        isDone = false;
        Logger logger = new Logger();
        logger.init("");
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return id + ". [T][" + status + "] " + super.toString();
    }

    @Override
    public String toTask() {
        return super.toTask() + "," + (isDone ? 1 : 0);
    }

    @Override
    public String getType() {
        return "todo";
    }
}