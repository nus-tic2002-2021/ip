package Duke.parser;

public enum CMD_Event_Enum_MTH {
    JAN("Jan"),
    FEB("Feb"),
    MAR("Mar"),
    APR("Apr"),
    MAY("May"),
    JUN("Jun"),
    JUL("Jul"),
    AUG("Aug"),
    SEP("Sep"),
    OCT("Oct"),
    NOV("Nov"),
    DEC("Dec")
    ;

    CMD_Event_Enum_MTH(String month) {
        this.month = month;
    }

    private String month;

    public String getMth() {
        return month;
    }

    public void setMth(String month) {
        this.month = month;
    }
}
