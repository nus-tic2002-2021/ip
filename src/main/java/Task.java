import java.util.ArrayList;

abstract class Task {
    protected String description;
    protected Boolean isDone;

    public Task(String description) {
        setDescription(description);
        isDone = false;
    }

    public Task(String description, Boolean isDone) {
        setDescription(description);
        this.isDone = isDone;
    }

    public void setDescription(String description) {
        this.description = description;
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
