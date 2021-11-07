import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private final static String STMT_END = "Bye. Hope to see you again soon!";
    private final static String STMT_START = "Hello! I'm Duke\nWhat can I do for you?";

    private final static String ERROR_PREFIX = "Oops I did not quite understand that.";

    public UI(TaskManager m) {
        this.manager = m;
    }

    Parser parser = new Parser();
    Scanner in = new Scanner(System.in);
    String input = "";
    Boolean isExit = false;
    TaskManager manager;

    public void start() {
        System.out.println(STMT_START);
    }

    public String getInput() {
        input = in.nextLine();
        return input;
    }

    public void end() {
        System.out.println(STMT_END);
    }

    public void parseInput() {
        parser.parseInput(input);
    }

    public Boolean isExit() {
        return isExit;
    }

    public void printErrorMessage(Message m) {
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

    public void processInput() {
        String instruction = parser.getInstruction();
        String taskInfo = parser.getTaskInfo();
        String firstToken = parser.getFirstToken();

        if (parser.isExit()) {
            isExit = true;
            return;
        }

        if (parser.isList()) {
            manager.listTasks();
        } else if (parser.isDone()) {
            manager.markTaskDone(Integer.parseInt((firstToken)));
        } else if (parser.isDelete()) {
            manager.deleteTask(Integer.parseInt((firstToken)));
        } else if (parser.isFind()) {
            ArrayList<Task> li = manager.findTask(firstToken);
            manager.listTasks(li);
        } else if (parser.isView()) {
            manager.viewTaskOn(parser.stringToDate(firstToken));
        } else {
            manager.addTask(manager.createTask(taskInfo, instruction));
        }
    }
}
