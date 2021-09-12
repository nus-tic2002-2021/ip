public class Task {
    String description;
    boolean isDone;
    char taskType;

    public Task(String description){
        this.description = description;
        isDone = false;
        taskType = '#';
    }

    public void showTask(){}

    public void doTask(){
        this.isDone = true;
    }
}