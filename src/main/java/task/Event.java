package task;

public class Event extends Task {
    protected String eventTime;
    
    public Event(String description, String eventTime) {
        super(description);
        setEvent(eventTime);
    }

    public Event(String description, String eventTime, Boolean isDone) {
        super(description);
        setEvent(eventTime);
        this.isDone = isDone;
    }

    public void setDone(){
        this.isDone = true;
        System.out.println("\tNice! I've marked this task as done:\n" +
                "\t  [E][X] " + description + "(at: " + eventTime + ")");
    }
    public void print(){
        if (isDone) {
            System.out.println("\t  [E][X] " + description + "(at: " + eventTime + ")");
        }
        else {
            System.out.println("\t  [E][ ] " + description + "(at: " + eventTime + ")");
        }

    }
    public void setEvent(String eventTime)
    {
        this.eventTime = eventTime;
    }

    public String getTask(){
        return "E";
    }
    public String getDescription(){
        return description;
    }
    public String getEventTime(){
        return eventTime;
    }

    public String getSave(){
        String s = getTask() + " | " +  getDone() + " | " + getDescription() + " | " + getEventTime();
        return s;
    }
    @Override
    public String toString() {
        String box = "[E][ ] ";
        if (isDone){
            box = "[E][X] ";
        }
        return (box + description + "(at: " + eventTime + ")");
    }
}
