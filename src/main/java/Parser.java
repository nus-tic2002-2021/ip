public class Parser {

    static void parseInput(String input) {
        if (input.trim().equals("list")) {
            TaskList.printTaskList();
        } else if (input.startsWith("todo ")) {
            AddTodo(input);
        } else if (input.startsWith("deadline ")) {
            AddDeadline(input);
        } else if (input.startsWith("event ")) {
            AddEvent(input);
        } else if (input.startsWith("done ")) {
            MarkDone(input);
        } else if (input.startsWith("delete ")) {
            DeleteTask(input);
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void AddTodo(String input) {
        try {
            if (InputChecker.CheckValidTodo(input)) {
                String newTask = input.substring(4).trim();
                Todo newTodo = new Todo(newTask);
                TaskList.addTaskToList(newTodo);
                Ui.PrintTaskAdded(newTodo);
                Ui.PrintTaskCount();
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static void AddDeadline(String input) {
        try {
            if (InputChecker.CheckValidDeadline(input)) {
                String[] parts = input.substring(8).split("/by");
                Deadline newDeadline = new Deadline(parts[0].trim(), parts[1].trim());
                TaskList.addTaskToList(newDeadline);
                Ui.PrintTaskAdded(newDeadline);
                Ui.PrintTaskCount();
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static void AddEvent(String input) {
        try {
            if (InputChecker.CheckValidEvent(input)) {
                String[] parts = input.substring(5).split("/at");
                Event newEvent = new Event(parts[0].trim(), parts[1].trim());
                TaskList.addTaskToList(newEvent);
                Ui.PrintTaskAdded(newEvent);
                Ui.PrintTaskCount();
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static void MarkDone(String input) {
        try {
            if (InputChecker.CheckValidDone(input)) {
                TaskList.markTaskAtIndex(input);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static void DeleteTask(String input) {
        try {
            if (InputChecker.CheckValidDelete(input)) {
                TaskList.DeleteIndex(input);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static Task ParseStorageLine(String FileLine) throws DukeException {
        String[] parts = FileLine.split("\\|");
        if (parts[0].trim().equals("T")) {
            if(FileLineChecker.CheckTodoLine(parts)) {
                Todo newTodo = getTodoFromLine(parts);
                return newTodo;
            }
        } else if (parts[0].trim().equals("D")) {
            if(FileLineChecker.CheckDeadlineLine(parts)) {
                Deadline newDeadline = getDeadlineFromLine(parts);
                return newDeadline;
            }
        } else if (parts[0].trim().equals("E")) {
            if (FileLineChecker.CheckEventLine(parts)) {
                Event newEvent = getEventFromLine(parts);
                return newEvent;
            }
        } else {
            System.out.println("Line is invalid");
        }
        throw new DukeException("A line from storage file is invalid and will not be added to Duke.");
    }

    public static Todo getTodoFromLine(String[] parts) {
        Todo newTodo = new Todo(parts[2].trim());
        if (parts[1].trim().equals("1")) {
            TaskList.MarkTask(newTodo);
        }
        return newTodo;
    }

    public static Deadline getDeadlineFromLine(String[] parts) {
        Deadline newDeadline = new Deadline(parts[2].trim(), parts[3].trim());
        if (parts[1].trim().equals("1")) {
            TaskList.MarkTask(newDeadline);
        }
        return newDeadline;
    }

    public static Event getEventFromLine(String[] parts) {
        Event newEvent = new Event(parts[2].trim(), parts[3].trim());
        if (parts[1].trim().equals("1")) {
            TaskList.MarkTask(newEvent);
        }
        return newEvent;
    }
}
