package Duke.task;

public class UnscheduledTask {
    protected String description;
    protected int duration;

    public UnscheduledTask(String desc, int duration){
        this.description = desc;
        this.duration = duration;
    }

    public String getDesc(){
        return this.description;
    }

    public int getDuration(){
        return this.duration;
    }

}
