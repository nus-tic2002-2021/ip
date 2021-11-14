import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Duke {

    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static String logoMessage = logo + "\nHello! I'm Duke. \nWhat can I do for you?";

    public static void printBreak() {
        System.out.println("____________________________________________________________");
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printTotalTask(int counter) {
        System.out.println("Now you have " + counter + " tasks in the list.");
    }

    public static void run(){
        System.out.println(logoMessage);
        printBreak();
        String line;
        ArrayList<Task> taskLists = new ArrayList<Task>();
        //Task[] lists = new Task[100];
        //int counter = 0;
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
                        if(taskLists.size()==0) {
                            printTotalTask(taskLists.size());
                            System.out.println("The list is empty.");
                            printBreak();
                            continue;
                        }
                        //for (int i = 0; i < counter; i++) {
                        //    System.out.println(i + 1 + ". " + lists[i].toString());
                        //}
                        Iterator<Task> iterator = taskLists.iterator();

                        while(iterator.hasNext()){
                            System.out.println(iterator.next());
                        }
                        printBreak();
                    } else if (desc.equalsIgnoreCase("done")) {
                        printBreak();
                        if (desc2.isEmpty()|| Integer.parseInt(desc2) > taskLists.size() || Integer.parseInt(desc2) <= 0){
                            throw new CommandNotFoundException();
                        }
                        int x = Integer.parseInt(desc2);
                        taskLists.get(x - 1).setDone(true);
                        //lists[x - 1].setDone(true);
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println(taskLists.get(x - 1).toString());
                        //System.out.println(lists[x - 1].toString());
                        printBreak();
                    } else if (desc.equalsIgnoreCase("delete")) {
                        if (taskLists.size()==0){
                            throw new NoListFoundException();
                        }
                        if (desc2.isEmpty()|| Integer.parseInt(desc2) > taskLists.size() || Integer.parseInt(desc2) <= 0){
                            throw new CommandNotFoundException();
                        }
                        printBreak();
                        int x = Integer.parseInt(desc2);
                        System.out.println("I've deleted this task entered: ");
                        System.out.println(taskLists.get(x - 1).toString());
                        taskLists.remove(x - 1);
                        printBreak();
                    } else if (desc.equalsIgnoreCase("deadline")) {
                        if (desc2.isEmpty()){
                            throw new deadlineNotFoundException();
                        }
                        printBreak();
                        String[] input = desc2.split("/by ", 2);
                        s1 = input[0];
                        s2 = input[1];
                        taskLists.add(new Deadline(s1, s2));
                        //lists[counter] = new Deadline(s1, s2);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(taskLists.get(taskLists.size()-1).toString());
                        //System.out.println(lists[x - 1].toString());
                        //counter++;
                        printTotalTask(taskLists.size());
                        printBreak();
                    } else if (desc.equalsIgnoreCase("event")) {
                        if (desc2.isEmpty()){
                            throw new eventNotFoundException();
                        }
                        printBreak();
                        String[] input = desc2.split("/at ", 2);
                        s1 = input[0];
                        s2 = input[1];
                        taskLists.add(new Event(s1,s2));
                        //lists[counter] = new Event(s1, s2);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(taskLists.get(taskLists.size()-1).toString());
                        //System.out.println(lists[counter].toString());
                        //counter++;
                        printTotalTask(taskLists.size());
                        printBreak();
                    } else if (desc.equalsIgnoreCase("todo")) {
                        if (desc2.isEmpty()){
                            throw new toDoNotFoundException();
                        }
                        printBreak();
                        taskLists.add(new ToDo(desc2));
                        //lists[counter] = new ToDo(desc2);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(taskLists.get(taskLists.size()-1).toString());
                        //System.out.println(lists[counter].toString());
                        //counter++;
                        printTotalTask(taskLists.size());
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
            } catch (NoListFoundException e){
                printBreak();
                System.out.println("Sorry, I am unable to delete any task because no task is found.");
                printBreak();
                continue;
            }catch (deadlineNotFoundException e){
                printBreak();
                System.out.println("Sorry, there cannot be any empty deadline task.");
                printBreak();
                continue;
            }catch (eventNotFoundException e){
                printBreak();
                System.out.println("Sorry, there cannot be any empty event task.");
                printBreak();
                continue;
            }
        }
        printBreak();
        printBye();
        printBreak();
    }

    public static void main(String[] args) throws CommandNotFoundException {
        //String logo = " ____        _        \n"
        //        + "|  _ \\ _   _| | _____ \n"
        //        + "| | | | | | | |/ / _ \\\n"
        //        + "| |_| | |_| |   <  __/\n"
        //        + "|____/ \\__,_|_|\\_\\___|\n";
        //        + "|____/ \\__,_|_|\\_\\___|\n";

        //System.out.println("Hello from\n" + logo);
        //printBreak();
        //System.out.println("Hello! I'm Duke ");
        //System.out.println("What can I do for you?");
        //printBreak();
        run();
    }
}
