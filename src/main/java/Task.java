import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class Task {
    protected String description;
    protected Integer id;

    public String toTask() {
        return getType() + "," + getDescription();
    }

    public Task(String description, int id) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        description = desc;
    }

    public String toString() {
        return description;
    }

    public String getType() {
        return "task";
    }
}

