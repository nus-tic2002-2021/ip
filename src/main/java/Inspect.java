import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inspect {
    protected String text;

    public Inspect(String text) {
        this.text = text;
    }

    public Boolean bye() {
        String[] words = {"bye", "cya"};
        boolean result = false;
        for (String i : words) {
            Pattern pattern = Pattern.compile(i, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(this.text);
            if (matcher.find()) {
                result = true;
                break;
            }
        }
        return result;
    }

    public Boolean addTask() {
        Pattern pattern = Pattern.compile("todo", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(this.text);
        return matcher.find();
    }

    public Boolean doneTask() {
        String[] words = {"done", "completed"};
        boolean result = false;
        for (String i : words) {
            Pattern pattern = Pattern.compile(i, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(this.text);
            if (matcher.find()) {
                result = true;
                break;
            }
        }
        return result;
    }

    public Boolean addDeadline() {
        Pattern pattern = Pattern.compile("deadline", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(this.text);
        return matcher.find();
    }
}