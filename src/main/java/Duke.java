import java.util.Scanner;

public class Duke {

    static Scanner in = new Scanner(System.in);
    static String line = "____________________________________________________________\n";
    static Task[] Checklist = new Task[100];
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
        Checklist[TaskCount] = newEntry;
        TaskCount++;
    }

    public static void PrintChecklist() {
        System.out.println(line + "\nHere are the tasks in your list:");
        for (int i = 0; i < TaskCount; i++) {
            System.out.println((i+1) + "." + Checklist[i].getTaskInfo());
        }
        System.out.println(line);
    }

    public static void MarkIndex(String input) {
        int index = Integer.parseInt(input.substring(4).trim()) - 1;
        if (index < TaskCount) {
            MarkTask(index);
            System.out.println(line + "Nice! I've marked this task as done:\n  "
                    + Checklist[index].getTaskInfo() + "\n" + line);
        } else {
            System.out.println(line + "\nInvalid task index number\n" + line);
        }
    }

    public static void MarkTask(int index) {
        Checklist[index].markCompleted();
    }

    public static void ExtendTaskList() {
        boolean stop = false;
        while (stop == false) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                TaskCount = 0;
                stop = true;
            } else if (input.equals("list")) {
                PrintChecklist();
            } else if (input.contains("todo") && (input.substring(0,4)).equals("todo")) {
                AddTodo(input);
            } else if (input.contains("deadline") && (input.substring(0,8)).equals("deadline")) {
                CheckValidDeadline(input);
            } else if (input.contains("event") && (input.substring(0,5)).equals("event")) {
                CheckValidEvent(input);
            } else if (input.contains("done") && (input.substring(0,4)).equals("done")) {
                if (input.length() < 5) {
                    System.out.println(line + "\nInput missing the index of the task that is done.\n"
                            + line);
                } else {
                    MarkIndex(input);
                }
            } else {
                System.out.println("Invalid input");
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
            throw new DukeException(line + "\nInvalid input, the task is missing.\n"
                    + line);
        } else {
            return true;
        }
    }

    public static void CheckValidDeadline(String input){
        if (input.contains("/by")) {
            String[] parts = input.substring(8).split("/by");
            if (parts.length != 2){
                System.out.println("Invalid deadline input. Missing info.");
            } else if (parts[0].trim().equals("")) {
                System.out.println(line + "\nInvalid input, the task is missing.\n" + line);
            } else if (parts[1].trim().equals("")) {
                System.out.println(line + "\nInvalid input, the deadline is missing.\n" + line);
            } else {
                Deadline newDeadline = new Deadline(parts[0].trim(), parts[1].trim());
                AddTask(newDeadline);
                PrintTaskAdded(newDeadline);
                PrintTaskCount();
                System.out.println(line);
            }
        } else {
            System.out.println("Invalid deadline input.");
        }
    }

    public static void CheckValidEvent(String input) {
        if (input.contains("/at")) {
            String[] parts = input.substring(5).split("/at");
            if (parts.length != 2) {
                System.out.println("Invalid event input. Missing info.");
            } else if (parts[0].trim().equals("")) {
                System.out.println(line + "\nInvalid input, the task is missing.\n" + line);
            } else if (parts[1].trim().equals("")) {
                System.out.println(line + "\nInvalid input, the deadline is missing.\n" + line);
            } else {
                Event newEvent = new Event(parts[0].trim(), parts[1].trim());
                AddTask(newEvent);
                PrintTaskAdded(newEvent);
                PrintTaskCount();
                System.out.println(line);
            }
        } else {
            System.out.println("Invalid event input.");
        }
    }

    public static void PrintTaskCount() {
        System.out.println("Now you have " + TaskCount + " tasks in the list.");
    }

    public static void PrintTaskAdded(Task newTask) {
        System.out.println(line + "\nGot it. I've added this task:\n"
                + newTask.getTaskInfo());
    }

    public static void Level4() {
        ExtendTaskList();
    }

    public static void main(String[] args) {
        StartDuke();
        Greet();
        Level4();
    }
}
