import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Hello command
        System.out.println("Hello, I'm Duke\n" + "What can I do for you?");
        //read line
        String line;
        Scanner in = new Scanner(System.in);
        List list = new List();
        System.out.println("________________________________________________");
        //to set what to do with the line in
        while(true){
            line = in.nextLine();
            //check for done statements, to update to DONE
            if (line.contains("done")) {
                System.out.println("Nice! I've marked this task as done:");
                int ref = Integer.parseInt(line.split(" ")[1]);
                list.setDone(ref - 1);
            }
            //switch statements for other conditions
            else {
                switch(line){
                    case "bye": System.out.println("\tBye! Thanks for visiting The Duke!");
                        break;

                    case "list": list.printList();
                        break;

                    default: list.addList(line);
                }
            }
        }
    }
}
