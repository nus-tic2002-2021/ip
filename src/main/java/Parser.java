
import java.util.ArrayList;

public class Parser extends Duke {

    protected final static String BYE = "Bye. Hope to see you again soon!\n";
    protected final static String NO_TASK = "You have no task in your list!\n";
    protected static ArrayList<Task> listOfTasks = new ArrayList<>();

    public static boolean parse(String cmd) throws DukeException {

        try {
            switch (cmd) {
                case "bye":
                    return parseBye();
                case "list":
                    return parseList(cmd);
                default:
                    return CmdsParser.parse(cmd);
            }
        }

        catch (DukeException e) {
            throw new DukeException();
        }

    }

    public static boolean parseBye () {
        System.out.print(LINE+BYE+LINE);
        return false;
    }

    public static boolean parseList(String cmd) {
        if (listOfTasks.size() == 0) System.out.print(LINE+NO_TASK+LINE);
        else {
            System.out.print(LINE + "Here are the tasks in your list:\n");
            for (Task task : listOfTasks) System.out.println(task.toString());
            System.out.print(LINE);
        }
        return true;
    }

}
