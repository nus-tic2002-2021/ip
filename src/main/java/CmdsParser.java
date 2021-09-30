
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
            case "delete":
                parseDelete();
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
            Task task = taskList.get(id-1);
            task.setDone();
            Printer.printDone(task);
        } catch (IndexOutOfBoundsException e) {
            Printer.printInvalidDone();
        }
    }

    public static void parseDelete() {
        try {
            cmdTask = cmds[1];
            int id = Integer.parseInt(String.valueOf(cmdTask));
            Task task = taskList.get(id-1);
            taskList.remove(id-1);
            Task.totalCount--;
            Printer.printDelete(task);
        } catch (IndexOutOfBoundsException e) {
            Printer.printInvalidDelete();
        }
    }

    public static void parseTodo(String cmd) {
        try {
            cmds = cmd.split("todo ", 2);
            cmdTask = cmds[1];
            Task newTask = new Todo(cmdTask);
            taskList.add(newTask);
            Printer.printTask(taskList);
        } catch (IndexOutOfBoundsException e) {
            Printer.printInvalidTodo();
        }
    }

    public static void parseEvent(String cmd) {
        try {
            cmds = cmd.split("event |/at", 3);
            cmdTask = cmds[1];
            cmdTaskType = cmds[2];
            Task newTask = new Event(cmdTask, cmdTaskType);
            taskList.add(newTask);
            Printer.printTask(taskList);
        } catch (IndexOutOfBoundsException e) {
            Printer.printInvalidEvent();
        }
    }

    public static void parseDeadline(String cmd) {
        try {
            cmds = cmd.split("deadline |/by", 3);
            cmdTask = cmds[1];
            cmdTaskType = cmds[2];
            Task newTask = new Deadline(cmdTask, cmdTaskType);
            taskList.add(newTask);
            Printer.printTask(taskList);
        } catch (IndexOutOfBoundsException e) {
            Printer.printInvalidDeadline();
        }
    }

}
