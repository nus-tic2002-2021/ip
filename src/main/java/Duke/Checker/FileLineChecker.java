package Duke.Checker;

/**
 * FileLine Checker contains methods to check whether
 * the lines from the files
 * contains valid task information.
 */
public class FileLineChecker {

    /**
     * Takes a string array and checks whether
     * it contains parts with valid information of a "to do" object.
     * @param parts
     * @return boolean
     */
    public static boolean isValidTodoLine(String[] parts) {
        assert(parts[0].trim().equals("T"));
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
     * it contains parts with valid information of a "deadline" object.
     * @param parts
     * @return boolean
     */
    public static boolean isValidDeadlineLine(String[] parts) {
        assert(parts[0].trim().equals("D"));
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
     * it contains parts with valid information of an "event" object.
     * @param parts
     * @return boolean
     */
    public static boolean isValidEventLine(String[] parts) {
        assert(parts[0].trim().equals("E"));
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
     * it contains parts with valid information of a "dowithin" object.
     * @param parts
     * @return
     */
    public static boolean isValidDoWithinLine(String[] parts) {
        assert(parts[0].trim().equals("W"));
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
