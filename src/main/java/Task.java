public class Task {
    String description;
    boolean isDone;
    Types type;

    public Task(String description){
        this.description = description;
        isDone = false;
        type = Types.UNKNOWN;
    }

    public void showTask(){}

    public void doTask(){
        this.isDone = true;
    }
}