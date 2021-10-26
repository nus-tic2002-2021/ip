package Duke.parser;

public enum CMD_Enum {
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    BYE("bye"),
    SAVE("save"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    FIND("find")
    ;
    /**
     * Eunm class for Command Constructor
     *
     */
    CMD_Enum(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
