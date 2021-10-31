package duke.command;

import duke.task.TaskList;

public class CommandResult {
    String respondToUser;
    TaskList taskList;

    public CommandResult(String respondToUser){
        this.respondToUser = respondToUser;
    }

    public String getRespondToUser() {
        return respondToUser;
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
