public class Task {
    protected String description;
    protected boolean isDone;
    protected char taskType;
    protected DateTime dateTime;

    public Task() {

    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Type type, DateTime dateTime) {
        this.description = description;
        switch (type) {
            case todo:
                this.taskType = 'T';
                break;
            case deadline:
                this.taskType = 'D';
                break;
            case event:
                this.taskType = 'E';
                break;
        }
        this.dateTime = dateTime;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public char getTaskType() {
        return taskType;
    }

    public String getDateTime() {
        return "(" + dateTime.getCondition() + ": " + dateTime.getTime() + ")";
    }
}