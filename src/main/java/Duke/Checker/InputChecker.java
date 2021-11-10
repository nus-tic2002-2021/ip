package Duke.Checker;
import Duke.DukeLogic.DukeException;

/**
 * Checks whether the input by the Duke user
 * contains valid task information.
 */
public class InputChecker {

    /**
     * Check whether input contains all the parts of a "to do" object.
     * Returns true if it contains all the respective parts.
     * @param input
     * @return boolean
     * @throws DukeException
     */
    public static boolean isValidTodoInput(String input) throws DukeException {
        if (input.length() < 5) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (input.substring(4).trim().equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            return true;
        }
    }

    /**
     * Checks whether input contains all that parts of a "deadline" object.
     * Returns true if it contains all the respective parts.
     * @param input
     * @return boolean
     * @throws DukeException
     */
    public static boolean isValidDeadlineInput(String input) throws DukeException {
        if (input.contains("/by")) {
            String[] parts = input.substring(8).split("/by");
            if (parts.length != 2) {
                throw new DukeException("☹ OOPS!!! Invalid syntax for adding deadline.");
            } else if (parts[0].trim().equals("")) {
                throw new DukeException("☹ OOPS!!! The task description of a deadline cannot be empty.");
            } else if (parts[1].trim().equals("")) {
                throw new DukeException("☹ OOPS!!! The due date/time of a deadline cannot be empty.");
            } else {
                return true;
            }
        } else {
            throw new DukeException("☹ OOPS!!! Invalid syntax for adding deadline.");
        }
    }

    /**
     * Checks whether input contains all that parts of a "event" object.
     * Returns true if it contains all the respective parts.
     * @param input
     * @return boolean
     * @throws DukeException
     */
    public static boolean isValidEventInput(String input) throws DukeException {
        if (input.contains("/at")) {
            String[] parts = input.substring(5).split("/at");
            if (parts.length != 2) {
                throw new DukeException("☹ OOPS!!! Invalid syntax for adding event.");
            } else if (parts[0].trim().equals("")) {
                throw new DukeException("☹ OOPS!!! The task description of an event cannot be empty.");
            } else if (parts[1].trim().equals("")) {
                throw new DukeException("☹ OOPS!!! The date/time of an event cannot be empty.");
            } else {
                return true;
            }
        } else {
            throw new DukeException("☹ OOPS!!! Invalid syntax for adding event.");
        }
    }

    /**
     * Checks whether input contains the enough and correct information
     * to mark a task as done.
     * @param input
     * @return boolean
     * @throws DukeException
     */
    public static boolean isValidDoneInput(String input) throws DukeException {
        if (input.length() < 5) {
            throw new DukeException("☹ OOPS!!! The index of the task to be marked as done is missing.");
        } else {
            try {
                int index = Integer.parseInt(input.substring(4).trim()) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! " +
                        "The index of the task to mark as done has to be an integer!");
            }
            return true;
        }
    }

    /**
     * Checks whether input contains the enough and correct information
     * to delete a task.
     * @param input
     * @return boolean
     * @throws DukeException
     */
    public static boolean isValidDeleteInput(String input) throws DukeException {
        if (input.length() < 7) {
            throw new DukeException("☹ OOPS!!! The index of the task to delete is missing.");
        } else {
            try {
                int test = Integer.parseInt(input.substring(6).trim()) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! " +
                        "The index of the task to delete has to be an integer!");
            }
            return true;
        }
    }

    public static boolean isValidFindInput(String[] parts) throws DukeException {
        if (parts.length != 2) {
            throw new DukeException("☹ OOPS!!! The keyword to find is missing.");
        } else {
            return true;
        }
    }
}
