package TaskList;

public class ToDos extends Task {
    protected String action;

    public ToDos(String description, String action) {
        super(description);
        this.action = action;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
