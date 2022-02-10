package task;

public enum Action {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private String addType;

    Action(String addType) {
        setAddType(addType);
    }

    public void setAddType(String addType) {
        this.addType = addType;
    }

    public String getAddType() {
        return addType;
    }

}
