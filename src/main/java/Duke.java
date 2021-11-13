import java.util.Scanner;

public class Duke {
    public static void printBreak() {
        System.out.println("____________________________________________________________");
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printTotalTask(int counter) {
        System.out.println("Now you have " + counter + " tasks in the list.");
    }


    public static void main(String[] args) throws CommandNotFoundException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        printBreak();
        System.out.println("Hello! I'm Duke ");
        System.out.println("What can I do for you?");
        printBreak();
        String line;
        Task[] lists = new Task[100];
        int counter = 0;
        String desc;
        String desc2 = "";
        String s1, s2;
        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            try {
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
                        printBreak();
                        for (int i = 0; i < counter; i++) {
                            System.out.println(i + 1 + ". " + lists[i].toString());
                        }
                        printBreak();
                    } else if (desc.equalsIgnoreCase("done")) {
                        printBreak();
                        int x = Integer.parseInt(desc2);
                        lists[x - 1].setDone(true);
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println(lists[x - 1].toString());
                        printBreak();
                    } else if (desc.equalsIgnoreCase("deadline")) {
                        printBreak();
                        String[] input = desc2.split("/by ", 2);
                        s1 = input[0];
                        s2 = input[1];
                        lists[counter] = new Deadline(s1, s2);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(lists[counter].toString());
                        counter++;
                        printTotalTask(counter);
                        printBreak();
                    } else if (desc.equalsIgnoreCase("event")) {
                        printBreak();
                        String[] input = desc2.split("/at ", 2);
                        s1 = input[0];
                        s2 = input[1];
                        lists[counter] = new Event(s1, s2);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(lists[counter].toString());
                        counter++;
                        printTotalTask(counter);
                        printBreak();
                    } else if (desc.equalsIgnoreCase("todo")) {
                        if (desc2.isEmpty()){
                            throw new toDoNotFoundException();
                        }
                        printBreak();
                        lists[counter] = new ToDo(desc2);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(lists[counter].toString());
                        counter++;
                        printTotalTask(counter);
                        printBreak();
                    } else {
                        throw new CommandNotFoundException();
                    }
                }
            } catch (toDoNotFoundException e ) {
                printBreak();
                System.out.println("Sorry, there cannot be any empty todo task.");
                printBreak();
                continue;
            } catch (CommandNotFoundException e){
                printBreak();
                System.out.println("Sorry, I am unable to handle your request.");
                printBreak();
                continue;
        }
    }
        printBreak();
        printBye();
        printBreak();
    }
}
