public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
        this.type = "T";
    }

    public void markDone(){
        this.isDone = true;
    }

    public void setDescription(String description) {
        this.description = description;
    };

    public String getDescription() { return this.description; }

    public String getType() { return this.type; }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
