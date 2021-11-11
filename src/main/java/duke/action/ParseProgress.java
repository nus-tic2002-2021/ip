package duke.action;

public class ParseProgress {

    public static String[] splitStringIntoSentence(String progress){
        return progress.split("\\n",-1);
    }

    public static String[] splitSentenceByBarSeparator(String progress){
        return progress.split("\\|",-1);
    }
}
