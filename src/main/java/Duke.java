import java.util.Scanner;

public class Duke {

    static Scanner in = new Scanner(System.in);
    static String line = "____________________________________________________________\n";
    static String[] list = new String[100];
    static int listCount = 0;
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

    public static void Echo() {
        Boolean stop = false;
        while (stop == false) {
            String word = in.nextLine();
            if (word.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again!\n" + line);
                stop = true;
            } else {
                System.out.println(line + word + "\n" + line);
            }
        }
    }

    public static void AddDisplayList() {
        Boolean stop = false;
        while (stop == false) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                listCount = 0;
                stop = true;
            } else if (input.equals("list")){
                PrintList();
            } else {
                list[listCount] = input;
                listCount++;
                System.out.println(line + "added: " + input + "\n" + line);
            }
        }
    }

    public static void PrintList() {
        System.out.println(line);
        for (int i = 0; i < listCount; i++) {
            System.out.println(i+1 + ". " + list[i]);
        }
        System.out.println(line);
    }

    public static void TaskList() {
        Boolean stop = false;
        while (stop == false) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                listCount = 0;
                stop = true;
            } else if (input.equals("list")){
                PrintChecklist();
            } else if ((input.length() > 3) && (input.substring(0,4)).equals("done") ) {
                if (input.length() < 5) {
                    System.out.println(line + "\nInput missing the index of the task that is done.\n"
                            + line);
                } else {
                    MarkIndex(input);
                }
            } else {
                Task newTask = new Task(input);
                AddTask(newTask);
                System.out.println(line + "added: " + input + "\n" + line);
            }
        }
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

    public static void Level1() {
        Greet();
        Echo();
    }

    public static void Level2() {
        Greet();
        AddDisplayList();
    }

    public static void Level3() {
        Greet();
        TaskList();
    }

    public static void main(String[] args) {
        StartDuke();
        // Level1();
        // Level2();
        Level3();
    }
}
