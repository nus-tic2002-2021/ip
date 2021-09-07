import java.util.Scanner;

public class Duke {
    public static void printbreak() {
        System.out.println("____________________________________________________________");
    }

    public static void printbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printtotaltask(int counter) {
        System.out.println("Now you have " + counter + " tasks in the list.");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        printbreak();
        System.out.println("Hello! I'm Duke ");
        System.out.println("What can I do for you?");
        printbreak();
        String line;
        Task[] lists = new Task[100];
        int counter = 0;
        String desc;
        String desc2 = "";
        String s1, s2;
        while (true) {

            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (!line.isEmpty()) {
                if (line.contains(" ")) {
                    String[] input = line.split(" ", 2);
                    desc = input[0];
                    desc2 = input[1];
                } else {
                    desc = line;
                }
                if (line.equalsIgnoreCase("bye"))
                    break;
                if (line.equalsIgnoreCase("list")) {
                    printbreak();
                    for (int i = 0; i < counter; i++) {
                        System.out.println(i + 1 + ". " + lists[i].toString());
                    }
                    printbreak();
                } else if (desc.equalsIgnoreCase("done")) {
                    printbreak();
                    int x = Integer.parseInt(desc2);
                    lists[x - 1].setDone(true);
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(lists[x - 1].toString());
                    printbreak();
                } else if (desc.equalsIgnoreCase("deadline")) {
                    printbreak();
                    String[] input = desc2.split("/by ", 2);
                    s1 = input[0];
                    s2 = input[1];
                    lists[counter] = new Deadline(s1, s2);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(lists[counter].toString());
                    counter++;
                    printtotaltask(counter);
                    printbreak();
                } else if (desc.equalsIgnoreCase("event")) {
                    printbreak();
                    String[] input = desc2.split("/at ", 2);
                    s1 = input[0];
                    s2 = input[1];
                    lists[counter] = new Event(s1, s2);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(lists[counter].toString());
                    counter++;
                    printtotaltask(counter);
                    printbreak();
                } else {
                    printbreak();
                    if (desc.equalsIgnoreCase("todo")){
                        lists[counter] = new ToDo(desc2);
                    }else {
                        lists[counter] = new ToDo(line);
                    }
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(lists[counter].toString());
                    counter++;
                    printtotaltask(counter);
                    printbreak();
                }
            }
        }
        printbreak();
        printbye();
        printbreak();
    }


}
