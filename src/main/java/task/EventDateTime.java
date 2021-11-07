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
    /**
     * Compares the event to be added with existing event to find any anomaly
     * where startTime of event to be added is between the existing event start and end time
     * Or endTime of event to be added is between the existing event start and end time.
     *
     * @param exist Date and time of existing task
     * @return true if anomaly detected else returns false
     */
    public Boolean isAnomaly( EventDateTime exist){
        if(getStartTime().isAfter(exist.getStartTime()) && getStartTime().isBefore(exist.getEndTime())){
            return true;
        }
        else if (getEndTime().isAfter(exist.getStartTime()) && getEndTime().isBefore(exist.getEndTime())){
            return true;
        }
        else if (getStartTime().isBefore(exist.getStartTime()) && getEndTime().isAfter(exist.getStartTime())){
            return true;
        }
        else if (getStartTime().isEqual(exist.getStartTime()) || getEndTime().isEqual(exist.getEndTime())){
            return true;
        }
        return false;
    }

}
