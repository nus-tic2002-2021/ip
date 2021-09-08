package Duke.classes.tasks;

import Duke.enums.TaskType;

public class Todo extends Task {
    public Todo(String description) {
        super(TaskType.TODO, description);
    }

    @Override
    public String toStatusString() {
        return "[T]" + super.toStatusString();
    }
}
