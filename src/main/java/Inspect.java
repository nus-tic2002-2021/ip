import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inspect {
    protected String text;
    public Inspect(String text){
        this.text = text;
    }

    public Boolean bye(){
        Pattern pattern = Pattern.compile("bye", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(this.text);
        return matcher.find();
    }
}
