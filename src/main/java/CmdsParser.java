
public class CmdsParser extends Parser {

    protected static String[] cmds = new String[3];
    protected static String cmdType;
    protected static String cmdTask;
    protected static String cmdTaskType;

    public static boolean parse(String cmd) throws DukeException {

        cmds = cmd.split(" ");
        cmdType = cmds[0];

        switch (cmdType) {
            case "done":
                parseDone();
                break;
            case "todo":
                parseTodo(cmd);
                break;
            case "event":
                parseEvent(cmd);
                break;
            case "deadline":
                parseDeadline(cmd);
                break;
            default:
                throw new DukeException();
        }

        return true;

    }

    public static void parseDone() {
        try {
            cmdTask = cmds[1];
            int id = Integer.parseInt(String.valueOf(cmdTask));
            for (Task task : listOfTasks) {
                if (task.getID() == id) {
                    task.setDone();
                    System.out.print(LINE + "Nice! I've marked this task as done.\n" + task.toString() + "\n" + LINE);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.print(LINE+INVALID_DONE+LINE);
        }
    }

    public static void parseTodo(String cmd) {
        try {
            cmds = cmd.split("todo ", 2);
            cmdTask = cmds[1];
            Task newTask = new Todo(cmdTask);
            listOfTasks.add(newTask);
            printTask();
        } catch (IndexOutOfBoundsException e) {
            System.out.print(LINE+INVALID_TODO+LINE);
        }
    }

    public static void parseEvent(String cmd) {
        try {
            cmds = cmd.split("event |/at", 3);
            cmdTask = cmds[1];
            cmdTaskType = cmds[2];
            Task newTask = new Event(cmdTask, cmdTaskType);
            listOfTasks.add(newTask);
            printTask();
        } catch (IndexOutOfBoundsException e) {
            System.out.print(LINE+INVALID_EVENT+LINE);
        }
    }

    public static void parseDeadline(String cmd) {
        try {
            cmds = cmd.split("deadline |/by", 3);
            cmdTask = cmds[1];
            cmdTaskType = cmds[2];
            Task newTask = new Deadline(cmdTask, cmdTaskType);
            listOfTasks.add(newTask);
            printTask();
        } catch (IndexOutOfBoundsException e) {
            System.out.print(LINE+INVALID_DEADLINE+LINE);
        }
    }

    public static void printTask() {
        System.out.print(LINE);
        System.out.println("Got it! I've added this task.\n" + listOfTasks.get(listOfTasks.size()-1).toString());
        System.out.println("Now you have " + listOfTasks.get(listOfTasks.size()-1).getTotalCount() + " in the list.");
        System.out.print(LINE);
    }

}
