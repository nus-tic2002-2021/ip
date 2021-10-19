import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static Scanner in = new Scanner(System.in);
    static String line = "____________________________________________________________\n";
    static ArrayList<Task> TaskList = new ArrayList<>();
    static int TaskCount = 0;

    public static void StartDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to\n" + logo + " chatbot! \n");
    }

    public static void Greet() {
        String start = "Hello, I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(line + start + line);
    }

    public static void AddTask(Task newEntry) {
        TaskList.add(newEntry);
        TaskCount++;
    }

    public static void PrintChecklist() {
        System.out.println(line + "\nHere are the tasks in your list:");
        for (int i = 0; i < TaskCount; i++) {
            System.out.println((i+1) + "." + TaskList.get(i).getTaskInfo());
        }
        System.out.println(line);
    }

    public static void MarkIndex(String input) throws DukeException {
        int index = Integer.parseInt(input.substring(4).trim()) - 1;
        if (index < TaskCount) {
            MarkTask(index);
            System.out.println(line + "Nice! I've marked this task as done:\n  "
                    + TaskList.get(index).getTaskInfo() + "\n" + line);
        } else {
            throw new DukeException(line + "\n☹ OOPS!!! " +
                    "The index number of the task to be done is invalid!\n" + line);
        }
    }

    public static void MarkTask(int index) {
        TaskList.get(index).markCompleted();
    }

    public static void ExtendTaskList() {
        boolean stop = false;
        while (stop == false) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                TaskCount = 0;
                stop = true;
            } else if (input.equals("list ")) {
                PrintChecklist();
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
                System.out.println(line + "☹ OOPS!!! " +
                        "I'm sorry, but I don't know what that means :-(\n" + line);
            }
        }
    }

    public static void AddTodo(String input) {
        try {
            if (CheckValidTodo(input)) {
                String newTask = input.substring(4).trim();
                Todo newTodo = new Todo(newTask);
                AddTask(newTodo);
                PrintTaskAdded(newTodo);
                PrintTaskCount();
                System.out.println(line);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static boolean CheckValidTodo(String input) throws DukeException {
        if (input.length() < 5) {
            throw new DukeException(line + "\n☹ OOPS!!! The description of a todo cannot be empty.\n"
                    + line);
        } else if (input.substring(4).trim().equals("")) {
            throw new DukeException(line + "\n☹ OOPS!!! The description of a todo cannot be empty.\n"
                    + line);
        } else {
            return true;
        }
    }

    public static void AddDeadline(String input) {
        try {
            if(CheckValidDeadline(input)){
                String[] parts = input.substring(8).split("/by");
                Deadline newDeadline = new Deadline(parts[0].trim(), parts[1].trim());
                AddTask(newDeadline);
                PrintTaskAdded(newDeadline);
                PrintTaskCount();
                System.out.println(line);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static boolean CheckValidDeadline(String input) throws DukeException{
        if (input.contains("/by")) {
            String[] parts = input.substring(8).split("/by");
            if (parts.length != 2){
                throw new DukeException("☹ OOPS!!! Invalid syntax for adding deadline.");
            } else if (parts[0].trim().equals("")) {
                throw new DukeException(line + "\n☹ OOPS!!! " +
                        "The task description of a deadline cannot be empty.\n" + line);
            } else if (parts[1].trim().equals("")) {
                throw new DukeException(line + "\n☹ OOPS!!! " +
                        "The due date/time of a deadline cannot be empty.\n" + line);
            } else {
                return true;
            }
        } else {
            throw new DukeException("☹ OOPS!!! Invalid syntax for adding deadline.");
        }
    }

    public static void AddEvent(String input) {
        try {
            if (CheckValidEvent(input)) {
                String[] parts = input.substring(5).split("/at");
                Event newEvent = new Event(parts[0].trim(), parts[1].trim());
                AddTask(newEvent);
                PrintTaskAdded(newEvent);
                PrintTaskCount();
                System.out.println(line);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static boolean CheckValidEvent(String input) throws DukeException {
        if (input.contains("/at")) {
            String[] parts = input.substring(5).split("/at");
            if (parts.length != 2) {
                throw new DukeException("☹ OOPS!!! Invalid syntax for adding event.");
            } else if (parts[0].trim().equals("")) {
                throw new DukeException(line + "\n☹ OOPS!!! " +
                        "The task description of an event cannot be empty.\n" + line);
            } else if (parts[1].trim().equals("")) {
                throw new DukeException(line + "\n☹ OOPS!!! " +
                        "The date/time of an event cannot be empty.\n" + line);
            } else {
                return true;
            }
        } else {
            throw new DukeException("☹ OOPS!!! Invalid syntax for adding event.");
        }
    }

    public static void MarkDone(String input) {
        try {
            if(CheckValidDone(input)) {
                MarkIndex(input);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static boolean CheckValidDone(String input) throws DukeException {
        if (input.length() < 5) {
            throw new DukeException(line + "\n☹ OOPS!!! " +
                    "The index of the task to be marked as done is missing.\n"
                    + line);
        } else {
            return true;
        }
    }

    public static void PrintTaskCount() {
        System.out.println("Now you have " + TaskCount + " tasks in the list.");
    }

    public static void PrintTaskAdded(Task newTask) {
        System.out.println(line + "\nGot it. I've added this task:\n"
                + newTask.getTaskInfo());
    }

    public static void DeleteTask(String input) {
        try {
            if (CheckValidDelete(input)) {
                DeleteIndex(input);
            }
        } catch (DukeException e){
            e.printErrMsg();
        }
    }

    public static boolean CheckValidDelete(String input) throws DukeException {
        if (input.length() < 7) {
            throw new DukeException(line + "\n☹ OOPS!!! " +
                    "The index of the task to delete is missing.\n"
                    + line);
        } else {
            try {
                int test = Integer.parseInt(input.substring(6).trim()) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException(line + "\n☹ OOPS!!! " +
                        "The index of the task to delete has to be an integer!\n" + line);
            }
            return true;
        }
    }

    public static void DeleteIndex(String input) throws DukeException {
        int index = Integer.parseInt(input.substring(6).trim()) - 1;
        if (index < TaskCount && index > -1) {
            String DeletedInfo = TaskList.get(index).getTaskInfo();
            RemoveTask(index);
            System.out.println(line + "Noted! I've removed this task:\n  "
                    + DeletedInfo + "\n" + line);
            PrintTaskCount();
        } else {
            throw new DukeException(line + "\n☹ OOPS!!! " +
                    "The index number of the task to delete is invalid!\n" + line);
        }
    }

    public static void RemoveTask(int index) {
        TaskList.remove(index);
        TaskCount--;
    }

    public static void main(String[] args) {
        StartDuke();
        Greet();
        ExtendTaskList();
    }
}
