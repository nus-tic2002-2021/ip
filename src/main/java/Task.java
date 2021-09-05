public class Task {
    String description;
    Integer id;

    public Task(String description, Integer id) {
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
}

