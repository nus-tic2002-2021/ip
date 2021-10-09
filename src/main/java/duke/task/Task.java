package duke.task;

public abstract class Task {
    String description;
    boolean isDone;
    Types type;

    public Task(String description){
        this.description = description;
        isDone = false;
        type = Types.UNKNOWN;
    }

    public abstract void showTask();

    public void doTask(){
        this.isDone = true;
    }

    public abstract String toStoreString();
}
