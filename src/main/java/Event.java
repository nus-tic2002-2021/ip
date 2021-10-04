public class Event extends DeadLine{
    private String clock;

    public Event(String des, String acro, String dat, String hour) {
        super(des, acro, dat);
        clock = hour;
    }


    @Override
    public String getClock() {
        return clock;
    }

    public void setClock(String input){
        clock = input;
    }

    @Override
    public String getDate() {
        return super.getDate(); // getting date from deadline class
    }
}
