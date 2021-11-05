package task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventDateTime {
    public LocalDateTime startTime;
    public LocalDateTime endTime;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public EventDateTime(LocalDateTime dateTime, LocalTime time){
        setEventDate(dateTime,time);
    }

    public void setEventDate (LocalDateTime dateTime, LocalTime time) {
            startTime = dateTime;
            int hours = time.getHour();
            int minutes = time.getMinute();
            endTime = startTime.plusHours(hours).plusMinutes(minutes);
    }

    public LocalDateTime getStartTime(){
        return startTime;
    }
    public LocalDateTime getEndTime(){
        return endTime;
    }

    public Boolean isAnomaly( EventDateTime exist){
        if(getStartTime().isAfter(exist.getStartTime()) && getEndTime().isBefore(exist.getEndTime())){
            return true;
        }
        else if (getStartTime().isBefore(exist.getStartTime()) && getEndTime().isAfter(exist.getStartTime())){
            return true;
        }
        else if (getEndTime().isAfter(exist.getStartTime()) && getEndTime().isBefore(exist.getEndTime())){
            return true;
        }
        else if (getStartTime().isEqual(exist.getStartTime()) || getEndTime().isEqual(exist.getEndTime())){
            return true;
        }
        return false;
    }

}
