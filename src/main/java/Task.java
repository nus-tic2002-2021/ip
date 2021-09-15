public class Task {

    private String description;
    private boolean isDone;


    public Task(){}

    public Task(String des){
        description = des;
        isDone = false;
    }

    public void setDone(boolean done){
        isDone = done;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    public String getDescription(){
        return description;
    }
}
