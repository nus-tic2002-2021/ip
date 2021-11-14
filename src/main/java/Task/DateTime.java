package Task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
    protected String condition;
    //protected String time;
    protected Date date;

    public DateTime() {

    }

    public DateTime(String condition, String time) {
        this.condition = condition;
        ///this.time = time;
        try {
            this.date =new SimpleDateFormat("dd/MM/yyyy HHmm").parse(time);
        } catch (Exception e) {

        }
    }

    public String getCondition() {
        return condition;
    }

//    public String getTime() {
//        return time;
//    }

    public String getTime() {
        return date.toString();
    }
}
