
import java.util.ArrayList;

public class Parser extends Duke {

    protected static ArrayList<Task> taskList = new ArrayList<>();

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
        printer.printBye();
        return false;
    }

    public static boolean parseList(String cmd) {
        if (taskList.size() == 0) printer.printNoTask();
        else printer.printList();
        return true;
    }

}
