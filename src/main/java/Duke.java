import java.util.Scanner;

public class Duke {
    static String command;
    static String task;
    static String condition;
    static DateTime dateTime;
    static Type type;
    static Boolean checkCommand;

    public static void initProgram() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

    }

    public static void terminateProgram() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0) ;
    }

    public static void exceptionCommand() {
        System.out.println("unexpected command");
        //System.exit(0) ;
    }


    public static void parseAddTask(String input) {
        String temp = "";

        temp = input.split(" ")[0];
        checkCommand = true;
        switch(temp) {
            case "todo":
                type = Type.todo;
                command = temp;
                break;
            case "deadline":
                type = Type.deadline;
                command = temp;
                break;
            case "event":
                type = Type.event;
                command = temp;
                break;
            default:
                System.out.println("invalid task command");
                checkCommand = false;
        }
        //System.out.println("Command:" + command);
        //remove command + space
        temp = input.replaceFirst(temp + " ", "");
        task = temp.split(" /")[0];
        //System.out.println("Task :" + task);
        condition = temp.split(" /")[1];
        parseCondition(condition);
        //System.out.println("Condition :" + condition);
    }
    public static void parseCondition(String input) {
        String temp[] = input.split(" ");
        dateTime = new DateTime(temp[0], temp[1]);
    }

    public static void main(String[] args) {

        initProgram();

        String line = "";
        Scanner in = new Scanner(System.in).useDelimiter("\\n");
        TaskList tl = new TaskList();
        Boolean isAddTask = true;

        while (true) {
            line = in.next();

            if(line.equals("bye")) {
                terminateProgram();
            } else if (line.equals("list")) {
                tl.printTaskList();
                isAddTask = false;
            } else if (line.contains("done")) {
                //System.out.println(line);
                int index = Integer.parseInt(line.split(" ")[1]);
                tl.setDone(index - 1);
                isAddTask = false;
            }

            if(isAddTask) {
                parseAddTask(line);
                if(checkCommand) {
                    tl.addTask(task,type, dateTime);
                }
                //System.out.println(line);
            }
            isAddTask = true;
        }

    }
}
