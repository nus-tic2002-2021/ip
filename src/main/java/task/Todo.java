package task;

import static task.Priority.printPriority;

/**
 * Todo is inheriting from Task
 */
public class Todo extends Task {
    /**
     * Deadline takes in input of String description
     * @param description description from Parser that user entered
     * super.type - Assigns type "T"
     * super.isDone - Assigns false to done
     */
    public Todo(String description) {
        super(description);
        super.type("T");
        super.isDone = false;
        super.priority = 0;
    }
    /**
     * Print Task that has been added to ArrayList
     */
    @Override
    public String printTask() {
        return super.printTask();
    }

}
