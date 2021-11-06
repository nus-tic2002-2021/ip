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

    public boolean containInfo(String info){
        double match = 0.6;
        int minSize = (int)Math.ceil(info.length() * match);
        for(int i = 0; i < info.length() - minSize + 1; i++){
            if(description.contains(info.substring(i, i + minSize))){
                return true;
            }
        }
        return false;
    }
}
