public class CommandResult {
    String respondToUser;
    TaskList taskList;

    CommandResult(String respondToUser){
        this.respondToUser = respondToUser;
    }

    public String getRespondToUser() {
        return respondToUser;
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
