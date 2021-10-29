package Duke.Checker;

public class FileLineChecker {
    public static boolean CheckTodoLine(String[] parts) {
        if (parts.length != 3) {
            return false;
        } else if (parts[2].trim().equals("")) {
            return false;
        } else if (!parts[1].trim().equals("1") && !parts[1].trim().equals("0")) {
            return false;
        }
        return true;
    }

    public static boolean CheckDeadlineLine(String[] parts) {
        if (parts.length != 4) {
            return false;
        } else if (parts[2].trim().equals("") || parts[3].trim().equals("")) {
            return false;
        } else if (!parts[1].trim().equals("1") && !parts[1].trim().equals("0")) {
            return false;
        }
        return true;
    }

    public static boolean CheckEventLine(String[] parts) {
        if (parts.length != 4) {
            return false;
        } else if (parts[2].trim().equals("") || parts[3].trim().equals("")) {
            return false;
        } else if (!parts[1].trim().equals("1") && !parts[1].trim().equals("0")) {
            return false;
        }
        return true;
    }
}
