package com.tic2002.task;

public class Event extends Task {
    /**
     * Deadline takes in input of String description, String at
     * @param description description from Parser that user entered
     * super.type - Assigns type "E"
     * super.timing - Stores String at in task timing
     * super.isDone - Assigns false to done
     * @Override - print task in Event format
     */
    public Event(String description, String at) {
        super(description);
        super.type("E");
        super.timing(at);
        super.isDone = false;
        super.priority = 0;
    }

    @Override
    public String printTask() {
        return super.printTask() + " (at: " + super.timing + ")";
    }
}
