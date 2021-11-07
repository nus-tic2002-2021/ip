import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;


public class Duke {
    // TODO: Change these to enums or configurations
    private final static String DETECT_END = "bye";
    private final static String DETECT_LIST = "list";
    private final static String DETECT_DONE = "done";
    private final static String DETECT_DELETE = "delete";
    private final static String DETECT_FIND = "find";
    private final static String DETECT_VIEW = "view";
    private final static String STMT_END = "Bye. Hope to see you again soon!";
    private final static String STMT_START = "Hello! I'm Duke\nWhat can I do for you?";
    private final static String ERROR_PREFIX = "Oops I did not quite understand that.";

    public static void printErrorMessage(Message m) {
        switch (m) {
            case ERROR_UNRECOGNISED:
                System.out.println(ERROR_PREFIX + " Unrecognised operation.");
                break;
            case ERROR_INVALID:
                System.out.println(ERROR_PREFIX + " Invalid input.");
                break;
            case EMPTY_LIST:
                System.out.println(ERROR_PREFIX + " Empty list.");
                break;
            case UNKNOWN_OBJECT:
                System.out.println(ERROR_PREFIX + " Could not find resource.");
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        // Init config
        Config config = new Config();

        // Init logger
        Logger logger = new Logger();
        logger.init(config.getLogPath());

        // Init task manager
        ArrayList<Task> list;
        TaskManager manager = new TaskManager();
        list = new StorageDTO(config.getStoragePath()).load(manager);

        // Init parser
        Parser parser = new Parser();

        // Program starts
        System.out.println(STMT_START);
        Scanner in = new Scanner(System.in);

        String input = in.nextLine();
        ArrayList<String> tokens = new ArrayList(Arrays.asList(input.split(" ")));
        String instruction = tokens.get(0);
        Integer id = 0;
        String taskInfo = tokens // TODO: Fix this (duh)
                .subList(1, tokens.size())
                .toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();

        while (!instruction.toLowerCase(Locale.ROOT).equals(DETECT_END)) {
            if (instruction.toLowerCase(Locale.ROOT).equals(DETECT_LIST)) {
                try {
                    manager.listTasks();
                } catch (Exception e) {
                    printErrorMessage(Message.EMPTY_LIST);
                }
            } else if (instruction.toLowerCase(Locale.ROOT).equals(DETECT_DONE) && tokens.size() >= 2) {
                manager.markTaskDone(Integer.parseInt((tokens.get(1))));
            } else if (instruction.toLowerCase(Locale.ROOT).equals(DETECT_DELETE) && tokens.size() >= 2) {
                try {
                    manager.deleteTask(list, Integer.parseInt((tokens.get(1))));
                } catch (Exception e) {
                    printErrorMessage(Message.UNKNOWN_OBJECT);
                }
            } else if (instruction.toLowerCase(Locale.ROOT).equals(DETECT_FIND) && tokens.size() >= 1) {
                ArrayList<Task> li = manager.findTask(tokens.get(1));
                manager.listTasks(li);
            } else if (instruction.toLowerCase(Locale.ROOT).equals(DETECT_VIEW) && tokens.size() >= 1) {
                manager.viewTaskOn(parser.stringToDate(tokens.get(1)));
            } else {
                manager.addTask(manager.createTask(taskInfo, instruction));
            }

            input = in.nextLine();
            System.out.println("input: " + input);
            tokens = new ArrayList(Arrays.asList(input.split(" ")));
            instruction = tokens.get(0);
            System.out.println("instruction: " + instruction);
            taskInfo = tokens
                    .subList(1, tokens.size())
                    .toString()
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", "")
                    .trim();
            System.out.println("task info:" + taskInfo);
            id = list.size();
        }

        new StorageDTO(FILEPATH_TASK).save(list);
        System.out.println(STMT_END);
    }
}
