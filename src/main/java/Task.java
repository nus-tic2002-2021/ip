import interfaces.Printable;

public class Task implements Printable {
    private String description;

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

    @Override
    public String toStatusString() {
        return "[ ] " + toString();
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
