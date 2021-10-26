public class Task {

    protected String Description;
    protected boolean Completed;

    public Task(String newTask) {
        this.Description = newTask;
        this.Completed = false;
    }

    public Task(String newTask, String toComplete) throws DukeException {
        this.Description = newTask;
        if (toComplete.equals("1")){
            this.Completed = true;
        } else if (toComplete.equals("0")) {
            this.Completed = false;
        } else {
            throw new DukeException("Invalid input");
        }
    }


    public String getDescription() {
        return Description;
    }

     public String getAdditionalInfo() {
        return "";
     }

    public String getCompletedSymbol() {
        if (Completed) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String getTaskInfo() {
        String TaskInfo = this.getCompletedSymbol() + " " + Description;
        return TaskInfo;
    }

    public void markCompleted() {
        Completed = true;
    }

    public String getTaskType() {
        return "";
    }
}
