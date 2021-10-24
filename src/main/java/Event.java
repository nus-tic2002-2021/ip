import java.time.LocalDate;
public class Event extends Task {
    LocalDate on;

    Event(String description, boolean isDone, LocalDate on) {
        super(description,isDone);
        this.on = on;
    }

    public static Task taskDecode(String s) {
    }


    @Override
    public Task complete() {
        return new Event(this.getName(), true, this.on);
    }

    @Override
    public Task setName(String New) {
        return new Event(New, isDone, this.on);
    }
    @Override
    public String toString() {
        String taskString = super.toString();
        String result = "[E]" + taskString + " (at: " + this.on + ")";
        return result;
    }
}
