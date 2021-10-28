public class Parser {

    static void parseInput(String input) {
        if (input.trim().equals("list")) {
            Duke.printTaskList();
        } else if (input.startsWith("todo ")) {
            Duke.AddTodo(input);
        } else if (input.startsWith("deadline ")) {
            Duke.AddDeadline(input);
        } else if (input.startsWith("event ")) {
            Duke.AddEvent(input);
        } else if (input.startsWith("done ")) {
            Duke.MarkDone(input);
        } else if (input.startsWith("delete ")) {
            Duke.DeleteTask(input);
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static boolean CheckValidTodo(String input) throws DukeException {
        if (input.length() < 5) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (input.substring(4).trim().equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            return true;
        }
    }

    public static boolean CheckValidDeadline(String input) throws DukeException {
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

    public static boolean CheckValidEvent(String input) throws DukeException {
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

    public static boolean CheckValidDone(String input) throws DukeException {
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

    public static boolean CheckValidDelete(String input) throws DukeException {
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


}
