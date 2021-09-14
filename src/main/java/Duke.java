import java.util.Scanner;

public class Duke {

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
                tl.addTask(line);
                //System.out.println(line);
            }
            isAddTask = true;
        }

    }
}
