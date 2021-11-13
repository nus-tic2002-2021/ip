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
    public static boolean isValidTodoLine(String[] parts) {
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
    public static boolean isValidDeadlineLine(String[] parts) {
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
    public static boolean isValidEventLine(String[] parts) {
        if (parts.length != 5) {
            return false;
        } else if (parts[2].trim().equals("") || parts[3].trim().equals("")) {
            return false;
        } else if (!parts[1].trim().equals("1") && !parts[1].trim().equals("0")) {
            return false;
        }
        return true;
    }

    public static boolean isValidDoWithinLine(String[] parts) {
        if (parts.length != 6) {
            return false;
        } else if (parts[2].trim().equals("") || parts[3].trim().equals("")
                || parts[4].trim().equals("")) {
            return false;
        } else if (!parts[1].trim().equals("1") && !parts[1].trim().equals("0")) {
            return false;
        }
        return true;
    }
}
