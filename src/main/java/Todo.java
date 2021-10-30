public class Todo extends Task{
    /**
     * ToDos: tasks without any date/time attached to it e.g., visit new theme park
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
