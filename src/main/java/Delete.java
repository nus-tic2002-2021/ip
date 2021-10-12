public class Delete extends Command {
    int taskNumToDelete;

    @Override
    public CommandResult execute() {
        Task removeTask = taskList.deleteTasks(taskNumToDelete);
        return new CommandResult(Message(removeTask));
    }
    public String Message(Task removeTask) {
        return "Noted! I've removed this task:\n"
                + removeTask + "\n"
                + "Now you have " + taskList.getSize() + " tasks in the list.";
    }
}
