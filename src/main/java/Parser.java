import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.logging.Logger;

public class Parser {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final static String DETECT_END = "bye";
    private final static String DETECT_LIST = "list";
    private final static String DETECT_DONE = "done";
    private final static String DETECT_DELETE = "delete";
    private final static String DETECT_FIND = "find";
    private final static String DETECT_VIEW = "view";

    ArrayList<String> tokens = new ArrayList<>();

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    String instruction;

    public String getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(String taskInfo) {
        this.taskInfo = taskInfo;
    }

    String taskInfo;

    public ArrayList<String> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<String> tokens) {
        this.tokens = tokens;
    }

    /**
     * @param date
     * @return date
     */
    public LocalDate stringToDate(String date) {
        LocalDate parsedDate = null;
        try {
            parsedDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            logger.info("parse date time got error" + e.getMessage());
        }
        return parsedDate;
    }

    public void parseInput(String input) {
        tokens = new ArrayList(Arrays.asList(input.split(" ")));
        instruction = tokens.get(0);
        taskInfo = tokens
                .subList(1, tokens.size())
                .toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();
    }

    public Boolean isExit() {
        return instruction.toLowerCase(Locale.ROOT).equals(DETECT_END);
    }

    public Boolean isExit(String i) {
        return i.toLowerCase(Locale.ROOT).equals(DETECT_END);
    }

    public Boolean isList() {
        return instruction.toLowerCase(Locale.ROOT).equals(DETECT_LIST);
    }

    public Boolean isList(String i) {
        return i.toLowerCase(Locale.ROOT).equals(DETECT_LIST);
    }

    public Boolean isDone() {
        return instruction.toLowerCase(Locale.ROOT).equals(DETECT_LIST) && tokens.size() >= 2;
    }

    public Boolean isDone(String i) {
        return i.toLowerCase(Locale.ROOT).equals(DETECT_DONE) && tokens.size() >= 2;
    }

    public Boolean isDelete() {
        return instruction.toLowerCase(Locale.ROOT).equals(DETECT_DELETE) && tokens.size() >= 2;
    }

    public Boolean isDelete(String i) {
        return i.toLowerCase(Locale.ROOT).equals(DETECT_DELETE) && tokens.size() >= 2;
    }

    public String getFirstToken() {
        if (tokens.size() >= 1) {
            return tokens.get(1);
        }

        return null;
    }

    public Boolean isFind() {
        return instruction.toLowerCase(Locale.ROOT).equals(DETECT_FIND) && tokens.size() >= 1;
    }

    public Boolean isFind(String i) {
        return i.toLowerCase(Locale.ROOT).equals(DETECT_FIND) && tokens.size() >= 1;
    }

    public Boolean isView() {
        return instruction.toLowerCase(Locale.ROOT).equals(DETECT_VIEW) && tokens.size() >= 1;
    }

    public Boolean isView(String i) {
        return i.toLowerCase(Locale.ROOT).equals(DETECT_VIEW) && tokens.size() >= 1;
    }
}
