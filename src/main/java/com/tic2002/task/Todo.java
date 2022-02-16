package com.tic2002.task;

/**
 * Todo is inheriting from Task
 */
public class Todo extends Task {
    /**
     * Deadline takes in input of String description
     * @param description description from Parser that user entered
     * super.type - Assigns type "T"
     * super.isDone - Assigns false to done
     * @Override - print task in Todo format
     */
    public Todo(String description) {
        super(description);
        super.type("T");
        super.isDone = false;
        super.priority = 0;
    }

    @Override
    public String printTask() {
        return super.printTask();
    }
}
