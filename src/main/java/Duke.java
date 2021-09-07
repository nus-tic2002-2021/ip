import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke ");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String line = "";
        Task[] lists = new Task[100];
        int counter = 0;
        while(true) {

            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if(line.equalsIgnoreCase("bye"))
                break;
            if(line.equalsIgnoreCase("list")){
                System.out.println("____________________________________________________________");
                for (int i = 0; i < counter; i++) {
                    System.out.println(i+1 + "." + lists[i].print());
                }
                System.out.println("____________________________________________________________");
            }else if (line.substring( 0 , 4 ).equalsIgnoreCase("done")) {
                System.out.println("____________________________________________________________");
                String[] input = line.split(" ",2);
                String desc = input[0];
                int x = Integer.parseInt(input[1]);;
                lists[x-1].setDone(true);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(lists[x-1].print());
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                lists[counter]= new Task(line);
                counter++;
                System.out.println("added: " + line);
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }


}
