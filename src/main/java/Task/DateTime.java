package Task;

public class DateTime {
    protected String condition;
    protected String time;

    public DateTime() {

    }

    public DateTime(String condition, String time) {
        this.condition = condition;
        this.time = time;
    }

    public String getCondition() {
        return condition;
    }

    public String getTime() {
        return time;
    }

}
