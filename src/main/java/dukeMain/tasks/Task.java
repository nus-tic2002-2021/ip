package dukeMain.tasks;

public class Task {
    private String item;
    private boolean done;

    public Task(String item) {
        this.item = item;
        this.done = false;
    }
    public Task(String item,boolean done){
        this.item = item;
        this.done = done;
    }

    public void setDone() {
        this.done = true;
    }

    public boolean isDone() {
        return this.done;
    }

    public String getStatusIcon() {
        return (this.done ? "X" : " "); // mark done task with X
    }

    public String getStatusNum(){
        return (this.done? "1" : "0");
    }
    public String getItem() {
        return this.item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return '['+ getStatusIcon() + "] " +
                 item ;
    }

    public String toStringSaveTask(String separator){
        return getStatusNum() + separator + getItem();
    }
}
