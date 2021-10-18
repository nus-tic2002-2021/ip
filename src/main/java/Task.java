public class Task {
    private String item;
    private boolean done;

    public Task(String item) {
        this.item = item;
        this.done = false;
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

    public String getItem() {
        return this.item;
    }

    public void setItem(String item) {
        this.item = item;
    }

}
