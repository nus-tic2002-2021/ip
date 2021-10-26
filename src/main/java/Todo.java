public class Todo extends Task{

    public Todo(String newTask) {
        super(newTask);
    }

    public String getCompletedSymbol() {
        if (Completed) {
            return "[T][X]";
        } else {
            return "[T][ ]";
        }
    }

    @Override
    public String getTaskType() {
        return "T";
    }
}
