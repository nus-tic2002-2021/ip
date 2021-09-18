public class Deadline extends Todo {
    String by;

    public Deadline(String description, String due, Integer id) {
        super(description, id);
        by = due;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String due) {
        by = due;
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return id + ". [D][" + status + "] " + super.getDescription() + " (by: " + by + ")";
    }
}