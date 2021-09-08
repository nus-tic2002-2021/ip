package classes.enums;

public enum TaskType {
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline");

    private String type;
    TaskType(String type) {
        setType(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return getType();
    }
}
