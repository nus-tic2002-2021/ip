package task;

import java.time.LocalDateTime;
import java.time.LocalTime;

abstract class Task {
    protected String description;
    protected Boolean isDone;
    protected String type;

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
    public abstract void setType();
    public abstract String getTask();
    public abstract String getDescription();
    public abstract String getSave();
    public LocalDateTime getDateTime(){
        return null;
    }
    public LocalTime getTime(){
        return null;
    }
    public String getType(){
        return type;
    }

    public String getDone(){
        if (isDone){
            return "1";
        }
        else{
            return "0";
        }
    }

}
