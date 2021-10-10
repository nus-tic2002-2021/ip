import java.util.ArrayList;

abstract class Task {
    protected String description;
    protected Boolean isDone;

    public Task(String description) {
        setDescription(description);
    }

    public void setDescription(String description) {
        this.description = description;
        isDone = false;
    }

    public abstract void setDone();
    public abstract void print();
    public abstract String getTask();
    public abstract String getDescription();
    public abstract String getSave();

    public String getDone(){
        if (isDone){
            return "1";
        }
        else{
            return "0";
        }
    }

}
