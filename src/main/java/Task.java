public class Task {
    private String description;
    private boolean isDone;

    public Task() {

    }

    public Task(String description) {
        setDescription(description);
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    @Override
    public String toString() {
        String output = "";

        if (isDone()) {
            output += "[X] ";
        } else {
            output += "[ ] ";
        }
        output += getDescription();
        return output;
    }
}
