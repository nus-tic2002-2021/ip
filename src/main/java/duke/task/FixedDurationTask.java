package duke.task;

public class FixedDurationTask {
    protected String description;
    protected int duration;

    /**
     * Creates FixedDuration Task.
     *
     * @param desc its fixed description.
     * @param duration its fixed duration.
     */
    public FixedDurationTask(String desc, int duration){
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
