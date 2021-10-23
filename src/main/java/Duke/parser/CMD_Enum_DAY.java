package Duke.parser;

public enum CMD_Enum_DAY {
    MON("Mon"),
    TUE("Tue"),
    WED("Wed"),
    THUR("Thur"),
    FRI("Fri")
    ;

    CMD_Enum_DAY(String day) {
        this.day = day;
    }

    private String day;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
