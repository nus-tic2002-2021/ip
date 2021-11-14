package task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String timing;

    public Task(){}

    public Task(String description) {
        this.description = description;
    }

    public void type(String type){
        this.type = type;
    }
    public void timing (String timing){
        this.timing = timing;
    }
    public void isDone(Boolean isDone) {this.isDone = isDone; }
    //set done
    public void setDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String getDone() { return (isDone ? "true" : "false");} //to save done status
    public String getDescription() {
        return description;
    }
    public String getType() { return type; }
    public String getTiming() { return timing; }
    public String printTask() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}
