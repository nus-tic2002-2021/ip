import java.awt.datatransfer.SystemFlavorMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class UI {
    private final static String STMT_END = "Bye 👋🏻😢. Dukie hopes to see you again soon!";
    private final static String STMT_DONE = "Nice! 🚀 Dukie has marked this task as done: ";
    private final static String ERROR_PREFIX = "Oops Dukie did not quite understand that.";
    private final static String STMT_DELETE = "Noted 👁👄👁. Dukie has removed this task: ";

    public UI(TaskManager m) {
        this.manager = m;
    }

    private final Parser parser = new Parser();
    private final Scanner in = new Scanner(System.in);
    private final TaskManager manager;
    private String input = "";
    private Boolean isExit = false;

    public String getGreeting() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        String msg = "";

        if (timeOfDay >= 0 && timeOfDay < 12) {
            msg = "Good morning ⛅️";
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            msg = "Good afternoon ☀️";
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            msg = "Good evening 🌥";
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            msg = "Good night 🌙";
        }
        msg += "! I'm Dukie 😇~\nWhat can I do for you?";
        return msg;
    }

    public void start() {
        System.out.println(getGreeting());
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
            Task t = manager.markTaskDone(Integer.parseInt((firstToken)));
            System.out.println(t == null ? "🤡 Oops that item doesn't exist, try \"list\" to see all your tasks." : STMT_DONE + "\n" + t);
        } else if (parser.isDelete()) {
            Task t = manager.deleteTask(Integer.parseInt((firstToken)));
            System.out.println(t == null ? "🤡 Oops that item doesn't exist, try \"list\" to see all your tasks." : STMT_DELETE + "\n " + t);
        } else if (parser.isFind()) {
            ArrayList<Task> li = manager.findTask(firstToken);
            if (li.size() == 0) {
                System.out.println("🤡 No tasks with keyword \"" + firstToken + "\", try \"list\" to see all your tasks.");
            } else {
                manager.listTasks(li);
            }
        } else if (parser.isView()) {
            ArrayList<Task> li = manager.viewTaskOn(parser.stringToDate(firstToken));
            if (li.size() == 0) {
                System.out.println("🤡 No tasks on \"" + firstToken + "\", try \"list\" to see all your tasks.");
            } else {
                manager.listTasks(li);
            }
        } else if (parser.isAdd()) {
            Task t = manager.createTask(taskInfo, instruction);
            System.out.println("Got it! 👌 I've added this " + t.getType() + ": \n" + t.toString());
        } else {
            System.out.println("🤡 Dukie did not understand the command, try again?");
        }
        System.out.println("You have " + manager.getNumOfTasks() + " task(s) in the main list 😇.");
    }
}
