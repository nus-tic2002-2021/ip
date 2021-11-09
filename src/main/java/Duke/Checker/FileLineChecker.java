package Duke.Checker;

/**
 * FileLine Checker contains methods to check whether
 * the lines from the files
 * contains valid task information.
 */
public class FileLineChecker {

    /**
     * Takes a string array and checks whether
     * it contains parts with information of a "to do" object.
     * @param parts
     * @return boolean
     */
    public static boolean CheckTodoLine(String[] parts) {
        if (parts.length != 4) {
            return false;
        } else if (parts[2].trim().equals("")) {
            return false;
        } else if (!parts[1].trim().equals("1") && !parts[1].trim().equals("0")) {
            return false;
        }
        return true;
    }

    /**
     * Takes a string array and checks whether
     * it contains parts with information of a "deadline" object.
     * @param parts
     * @return boolean
     */
    public static boolean CheckDeadlineLine(String[] parts) {
        if (parts.length != 5) {
            return false;
        } else if (parts[2].trim().equals("") || parts[3].trim().equals("")) {
            return false;
        } else if (!parts[1].trim().equals("1") && !parts[1].trim().equals("0")) {
            return false;
        }
        return true;
    }

    /**
     * Takes a string array and checks whether
     * it contains parts with information of a "event" object.
     * @param parts
     * @return boolean
     */
    public static boolean CheckEventLine(String[] parts) {
        if (parts.length != 5) {
            return false;
        } else if (parts[2].trim().equals("") || parts[3].trim().equals("")) {
            return false;
        } else if (!parts[1].trim().equals("1") && !parts[1].trim().equals("0")) {
            return false;
        }
        return true;
    }
}
