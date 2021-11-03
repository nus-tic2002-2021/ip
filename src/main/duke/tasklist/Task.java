package duke.tasklist;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String datetime;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //description.Getter
    public String getDescription() {return description;}

    //isDone.Setter
    public void setDone() {isDone = true;}

    public boolean isDone() { return isDone; }

    //isDone.Getter
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getType(){return type;}

    public String getDatetime(){
        return datetime;
    }

    public String printTask(){return "";}




}