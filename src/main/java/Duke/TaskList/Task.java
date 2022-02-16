package Duke.TaskList;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description,boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatusDone(){
        return (isDone ? "1" : "0"); // mark done task with 1
    }

    public void setDone(boolean done){
        this.isDone = done;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * This method returns the object in String format.
     *
     * @return the object in string format
     * */
    public String toString(){
        String print = "[" + getStatusIcon()+"] " + getDescription();
        return print;
    }
    /**
     * This method return the object in String format.
     *
     * @return the object in string format for the task to be saved.
     * */
    public String saveTask(){
        String save = getStatusDone() + "|" + getDescription();
        return save;
    }

}
