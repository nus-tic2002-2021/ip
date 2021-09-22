
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
            for (Task task : taskList) {
                if (task.getID() == id) {
                    task.setDone();
                    printer.printDone(task);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            printer.printInvalidDone();
        }
    }

    public static void parseTodo(String cmd) {
        try {
            cmds = cmd.split("todo ", 2);
            cmdTask = cmds[1];
            Task newTask = new Todo(cmdTask);
            taskList.add(newTask);
            printer.printTask(taskList);
        } catch (IndexOutOfBoundsException e) {
            printer.printInvalidTodo();
        }
    }

    public static void parseEvent(String cmd) {
        try {
            cmds = cmd.split("event |/at", 3);
            cmdTask = cmds[1];
            cmdTaskType = cmds[2];
            Task newTask = new Event(cmdTask, cmdTaskType);
            taskList.add(newTask);
            printer.printTask(taskList);
        } catch (IndexOutOfBoundsException e) {
            printer.printInvalidEvent();
        }
    }

    public static void parseDeadline(String cmd) {
        try {
            cmds = cmd.split("deadline |/by", 3);
            cmdTask = cmds[1];
            cmdTaskType = cmds[2];
            Task newTask = new Deadline(cmdTask, cmdTaskType);
            taskList.add(newTask);
            printer.printTask(taskList);
        } catch (IndexOutOfBoundsException e) {
            printer.printInvalidDeadline();
        }
    }

}
