public class   Todo extends Task {
    public boolean isDone;

    public Todo(String desc, Integer id) {
        super(desc, id);
        isDone = false;
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
}